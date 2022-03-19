package kr.study.dev_mook.model;

public class MyCalculator {
	
	private Calculator calculator;
	private Integer firstNumber;
	private Integer secondNumber;
	
	public MyCalculator() {}
	
	public void add() {
		calculator.addition(firstNumber, secondNumber);
	}
	
	public void sub() {
		calculator.subtraction(firstNumber, secondNumber);
	}
	
	public void multiple() {
		calculator.multiplication(firstNumber, secondNumber);
	}
	
	public void div() {
		calculator.division(firstNumber, secondNumber);
	}
	
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	public void setFirstNumber(Integer firstNumber) {
		this.firstNumber = firstNumber;
	}
	
	public void setSecondNumber(Integer secondNumber) {
		this.secondNumber = secondNumber;
	}

}
