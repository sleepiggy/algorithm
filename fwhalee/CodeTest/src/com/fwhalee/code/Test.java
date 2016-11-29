package com.fwhalee.code;

public class Test {

    public static void main(String[] args) {
    	
    	System.out.println(combination(499, 250));
        /*
        System.out.println(Double.MAX_VALUE);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        try {
            
            in = br.readLine();
            int caseNum = Integer.parseInt(in);
            System.out.println(caseNum);
            
            in = br.readLine();
            String[] componentNumArry = in.split("\\s+");
            
            int nodeNum = Integer.parseInt(componentNumArry[0]);
            int edgeNum = Integer.parseInt(componentNumArry[1]);
            
            System.out.println(nodeNum);
            System.out.println(edgeNum);
            
            in = br.readLine();
            String[] nodeInfoArry = in.split("\\s+");
            int source = Integer.parseInt(nodeInfoArry[0]);
            int dest = Integer.parseInt(nodeInfoArry[1]);
            float noise = Float.parseFloat(nodeInfoArry[2]);
            
            System.out.println(source);
            System.out.println(dest);
            System.out.println(noise);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
*/        
    }
    
    
	public static double combination(int n, int r) {
		
		double temp_n = n;
		double temp_r = r;
		double comb = 1;
		if (n != r && r != 0) {
			int count = r;
			for (int i = 0; i < count; i++) {
				comb *= temp_n / temp_r; 
				temp_n--;
				temp_r--;
			}
		}
		
		return comb;
	}
	
	
}
