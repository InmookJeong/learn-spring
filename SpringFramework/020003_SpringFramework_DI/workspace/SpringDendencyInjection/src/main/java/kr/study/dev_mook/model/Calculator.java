package kr.study.dev_mook.model;

public class Calculator {
	
	public void addition(Integer firstNumber, Integer secondNumber) {
		int result = firstNumber + secondNumber;
		System.out.println(firstNumber + " + " + secondNumber + " = " + result);
	}
	
	public void subtraction(Integer firstNumber, Integer secondNumber) {
		int result = firstNumber - secondNumber;
		System.out.println(firstNumber + " - " + secondNumber + " = " + result);
	}
	
	public void multiplication(Integer firstNumber, Integer secondNumber) {
		int result = firstNumber * secondNumber;
		System.out.println(firstNumber + " * " + secondNumber + " = " + result);
	}
	
	public void division(Integer firstNumber, Integer secondNumber) {
		int result = firstNumber / secondNumber;
		System.out.println(firstNumber + " / " + secondNumber + " = " + result);
	}

}
