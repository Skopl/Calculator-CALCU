package fileTester;

import java.io.*;
import java.util.Scanner;

public class MainTest {
	static public void main (String []args) throws Exception{
		//Scanner sc = new Scanner (System.in);
		String line = "";
		try(FileReader reader = new FileReader("D:\\Folder_for_Java_IDE\\WorkWithFiles\\src\\fileTester\\numbers.txt")){
			int c;
			while ((c=reader.read())!=-1) {
				//line = line.concat(String.valueOf(c));
				line = line.concat(String.valueOf((char)c));
			}
		}catch(IOException s) {
			System.out.println(s.getMessage());
		}
//		sc.close(); // �� ������� ��������� ����� !
		double a = 0.0;
		double b = 0.0;
		char znak = ' ';
		int m=0;
		boolean is = true; // ���������� ���� boolean ��� ���� ���-�� ������, ������� �� ���������� ��������� ��������� 
		for(String num:line.split(" ")) {
			m++;// ���� ������� ��� ���� ���-�� �������� ��� ������ �������� �� ����. �������� � ��� (45 % 60�) ���������� ����� 45 � 60�. ��� ��� �� ������� ��������� �����, �� , ��� �������� �� �������, ��� � ��� ���������� �������, ������ %, ������� �� ������� �� ������ ������������ 
			if(m==1) {
				try {
					a = Double.valueOf(num);	
				}catch(NumberFormatException e) {
					System.out.print("Error! Not number\n");
					is = false;
					break;
				}
			}
			if(m==3) {
				try {
					b = Double.valueOf(num);	
				}catch(NumberFormatException e) {
					System.out.print("Error! Not number\n");
					is = false;
					break;
				}
			}
		}
		if(is) {
		m=0; // �������� ��� ���������� ����� � ����
		for(String num:line.split(" ")) {
				m++;
				if(m==2) { // ��� �� ��������� ���� ������� �������� ������������
					try {
						znak = result(num,znak);
					}catch(Exception s){
						System.out.print("Operation Error!");
						is=false;
						break;
					}
				}
			}
		}
		if(is) {
			switch(znak){
			case('+'): System.out.print(a + b);
			break;
			case('-'): System.out.print(a - b);
			break;
			case('/'): 
				if(b==0.0) System.out.print("Error! Division by zero"); // ��, ��� ��� throw, ���� ������ � �� ������� ��� ��������, ������ ��� ������� �� 0 � �� 0.0 ����� ��� ���� �����, � ����� � ���, ����� � ��������� ������ �������� ������� � �������� )))
				else	 System.out.print(a / b);
			break;
			case('*'): System.out.print(a * b);
			break;
			}
		}

	}
	public static char result(String nms, char znak) throws Exception { //��� �� ����� ����� �������� � ��� ���� �����������, �� ��� � ������ �����, �� ������� ���� throw , ��� ��� ����� ���������� �� ��������� ���-�� ������������� ����� ��� ... �����, ���� ����� ��� ��������� 
		if(nms.equals("/") || nms.equals("*") || nms.equals("+") || nms.equals("-")) {
			znak = nms.charAt(0);
			return znak;
		}
		else throw new Exception("OperationError");//������ �������� ��� ��� ���������  ���������� �� �������� � �������. ������ ���, ���������, ������ ���� ����������� ��� ���������, ��� ��� ������ �� ������ ����� System.out.print ������� ������ ��� ���������.
	}
}


