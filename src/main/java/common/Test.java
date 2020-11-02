package common;

public class Test {
	public static void main(String[] args) {
		Boolean flag1 = true;
		Boolean flag2 = false;
		Boolean flag3 = true;
		
		if(flag1 == flag2) {
			System.out.println("123");
		}else {
			System.out.println("234");
		}
		
		if(flag1 == flag3) {
			System.out.println("124");
		}else {
			System.out.println("235");
		}
	}

}
