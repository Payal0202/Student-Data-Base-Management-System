import java.util.*;

public class SDBMS {

	Node head;
	public static int count = 0;

	static class Node {
		String name;
		String address;
		String branch;
		long phoneno;
		String division;
		Node next;
		Node prev;

		Node(String n, String a, String b, long p, String d) {
			n = name;
			a = address;
			b = branch;
			p = phoneno;
			d = division;
			next = null;
			prev = null;
		}
	}

	//METHOD FOR ADDING THE NEW STUDENT TO RECORD
	public static SDBMS create(SDBMS list, String n, String b, String a, long p, String d) {

		Node temp = new Node(n, b, a, p, d);
		temp.name = n;
		temp.address = a;
		temp.branch = b;
		temp.division = d;
		temp.phoneno = p;
		if (list.head == null) {
			list.head = temp;

		} else {
			Node curr = list.head;
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = temp;
			temp.prev = curr;
		}
		count++;
		return list;
	}

	//METHOD FOR DELETING THE EXISTING STUDENT FROM FIRST POSITION OF THE RECORD
	public static SDBMS delete_first(SDBMS list) {
		Node last = list.head;
		list.head = last.next;
		list.head.prev = null;
		last.next = null;
		count--;
		return list;
	}

	//METHOD FOR DELETING THE EXISTING STUDENT FROM LAST POSITION OF THE RECORD
	public static SDBMS delete_last(SDBMS list) {
		Node current = list.head;
		while (current.next.next != null) {
			current = current.next;
		}
		current.next = null;
		count--;
		return list;
	}

	//METHOD FOR DELETING THE EXISTING STUDENT FROM THE RECORD
	public static SDBMS delete_in_Between(SDBMS list, String name, long phoneno) {

		Node last = list.head;

		while (last.next != null && last.name != name && last.phoneno != phoneno) {
			last = last.next;
		}

		Node del = last;

		if (del.next == null) {
			Node one = del.prev;
			Node two = del.next;
			one.next = two;
			del.next = null;
			del.prev = null;
		} else {
			Node one = del.prev;
			Node two = del.next;
			one.next = two;
			two.prev = one;
			del.next = null;
			del.prev = null;
		}

		count--;
		return list;
	}

	//METHOD FOR SEARCHING THE STUDENT IN THE RECORD
	public static SDBMS searchElement(SDBMS list, String name, long phoneno) {
		Node last = list.head;

		while (last.next != null && last.name != name && last.phoneno != phoneno) {
			last = last.next;

		}
		Node search = last;
		if (search.name != name && search.phoneno != phoneno) {
			System.out.println("Data not found");
		} else {
			System.out.println("Data found!");
			System.out.println("Name of Student:" + last.name);
			System.out.println("Phone number:" + last.phoneno);
			System.out.println("Branch:" + last.branch);
			System.out.println("Division:" + last.division);
			System.out.println("Address:" + last.address);
		}

		return list;
	}

	//METHOD FOR DISPLAYING THE ALL EXISTING STUDENT RECORDS
	public static void displayDLL(SDBMS list) {

		Formatter fmt = new Formatter();
		System.out.format("%15s\t %15s\t %15s\t %15s\t %15s\n", "Name", "Branch", "Division", "PhoneNumber", "Address");
		Node currentNode = list.head;
		while (currentNode != null) {
			System.out.format("%15s\t %15s\t %15s\t %15s\t %15s\n", currentNode.name, currentNode.branch,
					currentNode.division, currentNode.phoneno, currentNode.address);
			currentNode = currentNode.next;
		}
	}

	//METHOD FOR DISPLAY THE TOTAL COUNT OF STUDENT IN THE RECORD
	public static void total_no_ofstudents() {
		System.out.println("The total number of students in the class room are " + count);
	}

	//MAIN METHOD
	public static void main(String[] args) {
		SDBMS list = new SDBMS();

		System.out.println("\t\t\t\t*STUDENT DATABASE MANAGEMENT SYSTEM*");
		System.out.println();

		Scanner sc = new Scanner(System.in);
		int ch = 0;
		do {
			System.out.println();
			System.out.println("MENU\n1. Add new student data\n2. Delete student data "
					+ "\n3. Delete first student's data \n4. Search student info"
					+ "\n5.Get total number of students \n6. EXIT ");
			ch = sc.nextInt();

			switch (ch) {
			case 1:
				int op;
				do {
					System.out.println("Enter student name ");
					String n = sc.next();
					System.out.println("Enter student Branch ");
					String b = sc.next();
					System.out.println("Enter student Address ");
					String a = sc.next();
					System.out.println("Enter student Phone number ");
					long p = sc.nextLong();
					System.out.println("Enter student Division ");
					String d = sc.next();

					list.create(list, n, b, a, p, d);

					System.out.println("Do you want add more , press 1 ");
					op = sc.nextInt();
				} while (op == 1);

				list.displayDLL(list);

				break;

			case 2:
				int obj;
				do {
					System.out.println("Enter student name that is to be deleted");
					String n = sc.next();
					System.out.println("Enter the Phone number of student that is to be deleted ");
					long p = sc.nextLong();

					list.delete_in_Between(list, n, p);

					System.out.println("Do you want delete more , press 1 ");
					obj = sc.nextInt();
				} while (obj == 1);
				System.out.println("Name   \t \t       Branch \t\t    Division  \t\t    Address \t\t    PhoneNumber");
				System.out.println();
				list.displayDLL(list);

				break;

			case 3:
				list.delete_first(list);
				System.out.println("Name   \t \t       Branch \t\t    Division  \t\t    Address \t\t    PhoneNumber");
				System.out.println();
				list.displayDLL(list);

				break;

			case 4:
				System.out.println("Enter the name of the student who's data is required");
				String n = sc.next();
				System.out.println("Enter phone number ");
				long p = sc.nextLong();

				list.searchElement(list, n, p);
				break;

			case 5:
				list.total_no_ofstudents();
				break;

			case 6:
				System.out.println("Loop terminated");
				break;

			default:
				System.out.println("Invalid choice");

			}

		} while (ch > 0 && ch <= 6);

	}
}
