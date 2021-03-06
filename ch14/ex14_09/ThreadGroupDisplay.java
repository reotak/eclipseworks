package ex14_09;

import java.io.StringWriter;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

class ThreadGroupDisplay {

	public static Thread runDisplay(final ThreadGroup g) {
		Thread disp = new Thread(new Runnable() {
			public void run() {
				for (;;) {
					Document doc = createDoc();

					// create and set root elem
					Element elem = doc.createElement("ThreadGroup");
					elem.setAttribute("name", g.getName());
					doc.appendChild(elem);

					analyzeThreadGroupChildsRecursive(g, elem, doc);

					try {
						String result = convertToString(doc);
						System.out.println(addDepth(result));

					} catch (TransformerException e1) {
						e1.printStackTrace();
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		disp.start();

		return disp;
	}

	/**
	 *
	 * @param g
	 *            調べたいスレッドグループ
	 * @param elem
	 *            このスレッドグループを登録する要素
	 * @param doc
	 *            調べた内容の保存先
	 */
	private static void analyzeThreadGroupChildsRecursive(ThreadGroup g,
			Element elem, Document doc) {

		Thread[] threads = new Thread[100];
		int countT = g.enumerate(threads, false);
		for (int i = 0; i < countT; i++) {
			Element e = doc.createElement("Thread");
			e.setAttribute("name", threads[i].getName());
			elem.appendChild(e);
		}

		ThreadGroup[] groups = new ThreadGroup[100];
		int countG = g.enumerate(groups, false);
		for (int i = 0; i < countG; i++) {
			Element e = doc.createElement("ThreadGroup");
			e.setAttribute("name", groups[i].getName());
			elem.appendChild(e);

			analyzeThreadGroupChildsRecursive(groups[i], e, doc);
		}
	}

	private static Document createDoc() {
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return documentBuilder.newDocument();
	}

	private static String convertToString(Node node)
			throws TransformerException {

		DOMSource source = new DOMSource(node);
		StringWriter swriter = new StringWriter();
		StreamResult result = new StreamResult(swriter);
		transform(source, result);
		return swriter.toString();
	}

	private static void transform(Source source, Result result)
			throws TransformerException {

		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		// tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.transform(source, result);
	}

	private static String addDepth(String xml) {
		String result = "";

		int depth = 0;
		Pattern doNotAddDepthPattern = Pattern.compile(".*/>");

		Pattern decDepthPattern = Pattern.compile(".*</");

		for (String line : xml.split("\n")) {
			if (decDepthPattern.matcher(line).find()) {
				depth--;
			} else if (!doNotAddDepthPattern.matcher(line).find()) {
				depth++;
			}

			for (int i = 0; i < depth; i++) {
				result += "\t";
			}
			result += line;
		}

		return result;
	}

	private static void startThreadHasLifeTime(final ThreadGroup group,
			final String name, final long lifeTimeMS) {
		new Thread(group, new Runnable() {
			public void run() {
				try {
					Thread.sleep(lifeTimeMS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, name).start();
	}

	public static void main(String[] args) {
		runDisplay(Thread.currentThread().getThreadGroup());

		ThreadGroup currentG = Thread.currentThread().getThreadGroup();
		startThreadHasLifeTime(currentG, "current-1: 10000MS", 10000);
		startThreadHasLifeTime(currentG, "current-2: 5000MS", 5000);

		ThreadGroup child1 = new ThreadGroup(Thread.currentThread()
				.getThreadGroup(), "Child1");
		startThreadHasLifeTime(child1, "child1-1: 2000MS", 2000);
		startThreadHasLifeTime(child1, "child1-2: 4000MS", 4000);

		ThreadGroup child2 = new ThreadGroup(Thread.currentThread()
				.getThreadGroup(), "Child2");
		startThreadHasLifeTime(child2, "child2-1: 6000MS", 6000);
		startThreadHasLifeTime(child2, "child2-2: 8000MS", 8000);

		ThreadGroup grandchild1_1 = new ThreadGroup(child1, "grandchild1_1");
		startThreadHasLifeTime(grandchild1_1, "grandchild1_1-1: 1000MS", 1000);
		startThreadHasLifeTime(grandchild1_1, "grandchild1_1-2: 5000MS", 5000);

		ThreadGroup grandchild1_2 = new ThreadGroup(child1, "grandchild1_2");
		startThreadHasLifeTime(grandchild1_2, "grandchild1_2-1: 2000MS", 2000);
		startThreadHasLifeTime(grandchild1_2, "grandchild1_2-2: 9000MS", 9000);
	}
}
