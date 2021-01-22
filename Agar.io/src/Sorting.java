import java.lang.reflect.Array;

public class Sorting {

	public static void main(String[] args) {
		int[] nums = {3, -5, 20, 50, 21};
		sort(nums);
		System.out.println((1592 / 10) * 10);
	}

	private static int[] sort(int[] nums) {
		boolean sorted = false;
		boolean done = false;
		
		while(!sorted) {
			int rand1 = (int)(Math.random()*nums.length);
			int rand2 = (int)(Math.random()*nums.length);
			
			int temp = rand1;
			nums[rand1] = rand2;
			nums[rand2] = temp;
			
			for(int i = 1; i < nums.length; i++) {
				if (nums[i] < nums[i-1]) {
					done = false;
					break;
				}
				if(i == nums.length - 1) done = true;
			}
			if(done) break;
		}
		return nums;
	}

}
