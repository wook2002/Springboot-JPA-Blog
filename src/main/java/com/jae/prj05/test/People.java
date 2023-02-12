package com.jae.prj05.test;

public class People {
//	private
	int hungryState = 50;
	
	public static void main(String[] args) {
		People p = new People();
		p.hungryState = 100;
		p.eat();
		System.out.println("asdf : "  + p.hungryState);
	}
	
	void eat() {
		hungryState += 10;
	}

}
