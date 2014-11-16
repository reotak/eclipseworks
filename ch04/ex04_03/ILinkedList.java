package ex04_03;

public interface ILinkedList {
	Object getData();

	void setData(Object data);

	LinkedList getNext();

	void setNext(LinkedList next);

	// この関数のみ巡回リストを考慮に入れている
	int countList(int maxCount) throws InfinityLoopException;
}
