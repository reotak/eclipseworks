package ex22_15;

import java.util.StringTokenizer;

import javax.naming.OperationNotSupportedException;

public class Calculator {

	public static double calc(String expr) throws OperationNotSupportedException {
		StringTokenizer st = new StringTokenizer(expr, " ");

		Stack<Double> stack = new Stack<Double>(10000);
		while (st.hasMoreTokens()) {
			try {
				calcToken(stack, st.nextToken());
			} catch (NumberFormatException e) {
				throw new OperationNotSupportedException("未定義の演算子が指定されました");
			} catch (IndexOutOfBoundsException | NullPointerException e) {
				throw new IllegalArgumentException("不適切な数式です");
			}
		}

		Double result;
		try {
			result = stack.pop();
			if (stack.pop() != null) { // スタックに値が余っていたら
				throw new IllegalArgumentException("不適切な数式です");
			}
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			throw new IllegalArgumentException("不適切な数式です");
		}

		return result;
	}

	private static void calcToken(Stack<Double> stack, String token) {
		if (token.equals("+")) {
			stack.push(stack.pop() + stack.pop());
			return;
		} else 	if (token.equals("-")) {
			stack.push(stack.pop() - stack.pop());
			return;
		} else 	if (token.equals("*")) {
			stack.push(stack.pop() * stack.pop());
			return;
		} else 	if (token.equals("/")) {
			stack.push(stack.pop() / stack.pop());
			return;
		} else if (token.equals("sin")) {
			stack.push(Math.sin(stack.pop()));
			return;
		} else if (token.equals("cos")) {
			stack.push(Math.cos(stack.pop()));
			return;
		} else if (token.equals("tan")) {
			stack.push(Math.tan(stack.pop()));
			return;
		} else if (token.equals("asin")) {
			stack.push(Math.asin(stack.pop()));
			return;
		} else if (token.equals("acos")) {
			stack.push(Math.acos(stack.pop()));
			return;
		} else if (token.equals("atan")) {
			stack.push(Math.atan(stack.pop()));
			return;
		} else if (token.equals("atan2")) {
			stack.push(Math.atan2(stack.pop(), stack.pop()));
			return;
		} else if (token.equals("toRadians")) {
			stack.push(Math.toRadians(stack.pop()));
			return;
		} else if (token.equals("toDegrees")) {
			stack.push(Math.toDegrees(stack.pop()));
			return;
		} else if (token.equals("exp")) {
			stack.push(Math.exp(stack.pop()));
			return;
		} else if (token.equals("sinh")) {
			stack.push(Math.sinh(stack.pop()));
			return;
		} else if (token.equals("cosh")) {
			stack.push(Math.cosh(stack.pop()));
			return;
		} else if (token.equals("tanh")) {
			stack.push(Math.tanh(stack.pop()));
			return;
		} else if (token.equals("pow")) {
			stack.push(Math.pow(stack.pop(), stack.pop()));
			return;
		} else if (token.equals("log")) {
			stack.push(Math.log(stack.pop()));
			return;
		} else if (token.equals("log10")) {
			stack.push(Math.log10(stack.pop()));
			return;
		} else if (token.equals("sqrt")) {
			stack.push(Math.sqrt(stack.pop()));
			return;
		} else if (token.equals("cbrt")) {
			stack.push(Math.cbrt(stack.pop()));
			return;
		} else if (token.equals("signum")) {
			stack.push(Math.signum(stack.pop()));
			return;
		} else if (token.equals("ceil")) {
			stack.push(Math.ceil(stack.pop()));
			return;
		} else if (token.equals("floor")) {
			stack.push(Math.floor(stack.pop()));
			return;
		} else if (token.equals("rint")) {
			stack.push(Math.rint(stack.pop()));
			return;
		} else if (token.equals("round")) {
			stack.push((double)Math.round(stack.pop()));
			return;
		} else if (token.equals("abs")) {
			stack.push(Math.abs(stack.pop()));
			return;
		} else if (token.equals("max")) {
			stack.push(Math.max(stack.pop(), stack.pop()));
			return;
		} else if (token.equals("min")) {
			stack.push(Math.min(stack.pop(), stack.pop()));
			return;
		} else if (token.equals("hypot")) {
			stack.push(Math.hypot(stack.pop(), stack.pop()));
			return;
		} else {
			stack.push(new Double(token));
			return;
		}
	}
}
