package com.AJPTech.moderncalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends Activity {
	public String equation = "";
	public double answer = 0.0;
	public double memory = 0.0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}
	private void displayText(){
		TextView out = (TextView) findViewById(R.id.output);
		out.setText(equation);
	}
	private void displayAnswer(){
		TextView out = (TextView) findViewById(R.id.output);
		out.setText(Double.toString(answer));
	}
	public void onClick(View view){
		if (view instanceof Button){
			String text = ((Button)view).getText().toString();
			if (text.equals("C")){
				equation = "";			
			} else if (text.equals("CE")){
		
			} else if (text.equals("MC")){
				memory = 0;
			} else if (text.equals("MR")){
				equation += Double.toString(memory);
			} else if (text.equals("=")){
				splitEquation();
				displayAnswer();
				return;
			} else if (text.equals("√")){
				answer = Math.sqrt(Double.parseDouble(equation));
				displayAnswer();
				return;
			} else if (text.equals("(-)")){
				equation += text;
			} else if (text.equals("M+")){
				memoryIncrement(Double.parseDouble(text));
			} else if (text.equals("M-")){
				memoryDecrement(Double.parseDouble(text));
			} else {
				if (text.equals("+") || text.equals("-") || text.equals("×") || text.equals("÷") || text.equals("%")){
					if (isOperatorPresent() == false || equation.contains("%") == false ){
						equation += text;
					}
				} else {
					equation+= text;
				}
			}			
		}
		displayText();
	}
	private void splitEquation(){
		String firstDouble = "", secondDouble = "";
		char operator = ' ';
		boolean error = false;
		if (equation.contains("+") == true){
			firstDouble = equation.substring(0, equation.indexOf("+"));
			secondDouble = equation.substring(equation.indexOf("+") + 1, equation.length());
			operator = '+';
		} else if (equation.contains("-") == true){
			firstDouble = equation.substring(0, equation.indexOf("-"));
			secondDouble = equation.substring(equation.indexOf("-") + 1, equation.length());
			operator = '-';
		} else if (equation.contains("×") == true){
			firstDouble = equation.substring(0, equation.indexOf("×"));
			secondDouble = equation.substring(equation.indexOf("×") + 1, equation.length());
			operator = '*';
		} else if (equation.contains("÷") == true){
			firstDouble = equation.substring(0, equation.indexOf("÷"));
			secondDouble = equation.substring(equation.indexOf("÷") + 1, equation.length());
			operator = '/';
		} else {
			System.out.println("The operation has no operator, please try again.");
			error = true;
		}
		solveEquation(firstDouble, secondDouble, operator);
	}
	private void solveEquation(String firstDouble, String secondDouble, char operator){
		double first, second;
		first = Double.parseDouble(firstDouble);
		if (secondDouble.endsWith(Character.toString('%'))){
			second = (first * 0.01 * Double.parseDouble(secondDouble.substring(0, secondDouble.length() - 1)));
		} else {
			second = Double.parseDouble(secondDouble);
		}
		if (operator == '+'){
			answer = first + second;
		} else if (operator == '-'){
			answer = first - second;
		} else if (operator == '*'){
			answer = first * second;
		} else if (operator == '/'){
			answer = first / second;
		}
		equation = "";
	}
	
	private void memoryIncrement(double num){
		memory += num;
	}
	private void memoryDecrement(double num){
		memory -= num;
	}
	private boolean isOperatorPresent(){
		return (equation.contains("+")) || (equation.contains("- ")) || (equation.contains("×")) || (equation.contains("÷"));
	}


}



