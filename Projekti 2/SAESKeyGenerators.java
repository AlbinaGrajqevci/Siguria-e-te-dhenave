package exercises;

public class SAESKeyGenerators {
	public static byte[] keyGenerators(byte[] K0) {
		byte[] K1 = new byte[16];
		byte k0 = K0[0];
		byte k1 = K0[1];
		byte k2 = K0[2];
		byte k3 = K0[3];
		// K1
		byte k4 = (byte)(k0 ^ g(k3, 1));
		byte k5 = (byte)(k4 ^ k1);
		byte k6 = (byte)(k5 ^ k2);
		byte k7 = (byte)(k6 ^ k3);
		// K2 
		byte k8 = (byte)(k4 ^ g(k7, 2));
		byte k9 = (byte)(k8 ^ k5);
		byte k10 = (byte)(k9 ^ k6);
		byte k11 = (byte) (k10 ^ k7);
		// K3
		byte k12 = (byte)(k8 ^ g(k11, 3));
		byte k13 = (byte) (k12 ^ k9);
		byte k14 = (byte) (k13 ^ k10);
		byte k15 = (byte) (k14 ^ k11);
		K1[0] = k0;
		K1[1] = k1;
		K1[2] = k2;
		K1[3] = k3;
		K1[4] = k4;
		K1[5] = k5;
		K1[6] = k6;
		K1[7] = k7;
		K1[8] = k8;
		K1[9] = k9;
		K1[10] = k10;
		K1[11] = k11;
		K1[12] = k12;
		K1[13] = k13;
		K1[14] = k14;
		K1[15] = k15;
		return K1;
	}
	
	public static byte g(byte k3, int i) {
		SAES s = new SAES();
		int[] b = s.decimalToBinary(k3);
		b = leftshift(b);
		byte d = (byte)(b[3] * 1 + b[2] * 2 + b[1] * 4 + b[0] * 8);
		byte result = 0;
		if (i == 1) {
			result =(byte) (d ^ 1);
		} else if (i == 2) {
			result = (byte) (d ^ 2);
		} else if (i == 3) {
			result = (byte) (d ^ 4);
		}
		return result;
	}
	
	public static int[] leftshift(int[] array) {
		// Perform a circular left shift on the array
		int temp = array[0];
		for (int i = 0; i < array.length - 1; i++) {
		    array[i] = array[i+1];
		}
		array[array.length - 1] = temp;
		return array;
	}
	
	public static void main(String[] args) {
		SAES s = new SAES();
		byte[] k0 = {12, 15, 10, 1};
		//byte[] k0 = {10, 7, 3, 11};
		byte[] K = keyGenerators(k0);
		for (int i = 0; i < K.length; i++) {
			System.out.print(K[i] + " ");
		}
		System.out.println();
		byte[] K1 = new byte[4];
		byte[] K2 = new byte[4];
		byte[] K3 = new byte[4];
		for (int i = 0; i < 4; i++) {
			K1[i] = K[i + 4];
			K2[i] = K[i + 8];
			K3[i] = K[i + 12];
		}
		s.printArray(K1);
		s.printArray(K2);
		s.printArray(K3);
		
	}
}
