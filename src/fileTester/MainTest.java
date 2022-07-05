package fileTester;

import java.io.*;
import java.util.Scanner;

public class MainTest {
	static public void main (String []args) throws Exception{
		String line = "";
		try(FileReader reader = new FileReader("D:\\Folder_for_Java_IDE\\WorkWithFiles\\src\\fileTester\\numbers.txt")){
			int c;
			while ((c=reader.read())!=-1) {
				if(c==13) {
					System.out.print(line+" = ");
					calculate(line);
					line = "";
					continue;
				}
				line = line.concat(String.valueOf((char)c));
			}
		}catch(IOException s) {
			System.out.println(s.getMessage());
		}
		System.out.print(line+" = ");
		calculate(line);
	}
	
	public static char result(String nms, char znak) throws Exception { //так то можно легко обойтись и без этой конструкции, но как я сказал ранее, мы изучаем тему throw , так что нужно изгибаться по максимуму что-бы реализовывать через эти ... дебри, если можно так выразится 
		if(nms.equals("/") || nms.equals("*") || nms.equals("+") || nms.equals("-")) {
			znak = nms.charAt(0);
			return znak;
		}
		else throw new Exception("OperationError");//Обрати вниманеи что это сообщение  отличается от заданого в условии. Именно это, сообщение, должно быть читабельным для программы, так что дальше мы просто через System.out.print выведем нужное нам сообщение.
	}
	
	public static void calculate(String line) throws Exception{
		FileWriter writer = new FileWriter("D:\\Folder_for_Java_IDE\\WorkWithFiles\\src\\fileTester\\results.txt",true);
		double result = -0.1;
		double a = 0.0;
		double b = 0.0;
		char znak = ' ';
		int m=0;
		boolean is = true; // переменная типа boolean для того что-бы понять, логично ли продолжить выполнять программу 
		for(String num:line.split(" ")) {
			m++;// этот счетчик для того что-бы вытянуть нам нужное значение из трех. Допустим у нас (45 % 60ы) вытягиваем число 45 и 60ы. Так как мы сначала проверяем числа, то , как сказанно по условию, они у нас проверятся первыми, нежели %, которое по условию не должно существовать 
			if(m==1) {
				try {
					a = Double.valueOf(num);	
				}catch(NumberFormatException e) {
					System.out.print("Error! Not number");
					try {
						writer.write(line + " = "+ "Error! Not number");
						System.out.print("\nNow this in your file\n");
						writer.flush();
					} catch (IOException ex) {
						System.out.print(ex.getMessage());
					} 
					
					is = false;
					break;
				}
			}
			if(m==3) {
				try {
					b = Double.valueOf(num);	
				}catch(NumberFormatException e) {
					System.out.print("Error! Not number");
					try {
						writer.write(line + " = "+ "Error! Not number");
						System.out.print("\nNow this in your file\n");
						writer.flush();
					} catch (IOException ex) {
						System.out.print(ex.getMessage());
					} 
					
					is = false;
					break;
				}
				if(b==0.0) {
					System.out.print("Error! Division by zero");
					try {
						writer.write(line + " = "+ "Error! Division by zero");
						System.out.print("\nNow this in your file\n");
						writer.flush();
					} catch (IOException ex) {
						System.out.print(ex.getMessage());
					} 
					is = false;
					break;// да, тут без throw, если честно я не пытался его написать, потому что деление на 0 и на 0.0 вроде как тоже самое, а вроде и нет, лучше в некоторых местах опустить условие и схитрить )))
				}
			}
		}
		if(is) {
		m=0; // обнуляем для следующего входа в цикл
		for(String num:line.split(" ")) {
				m++;
				if(m==2) { // тут мы проверяем знак который поставил пользователь
					try {
						znak = result(num,znak);
					}catch(Exception s){
						System.out.print("Operation Error!");
						try {
							writer.write(line + " = "+ "Operation Error!");
							System.out.print("\nNow this in your file\n");
							writer.flush();
						} catch (IOException ex) {
							System.out.print(ex.getMessage());
						} 
						is=false;
						break;
					}
				}
			}
		}
		if(is) {
			switch(znak){
			case('+'): result = a + b;
			break;
			case('-'): result = a - b;
			break;
			case('/'): result = a / b;
			break;
			case('*'): result = a * b;
			break;
			}
			System.out.print(result);
			try {
				writer.write(line + " = "+result);
				System.out.print("\nNow this in your file\n");
				writer.flush();
			} catch (IOException ex) {
				System.out.print(ex.getMessage());
			}
		}
	}
}


