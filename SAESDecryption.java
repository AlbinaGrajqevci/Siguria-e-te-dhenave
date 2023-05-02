package exercises;

public class SAESDecryption {
	public static byte[] decryption(byte[] C) {
		SAES s = new SAES();
		SAESKeyGenerators k = new SAESKeyGenerators();
		//byte[] K0 = {12, 15, 10, 1};
		byte[] k0 = {10, 7, 3, 11};
		byte[] K = k.keyGenerators(k0);
		byte[] K1 = new byte[4];
		for (int i = 0; i < 4; i++) {
			K1[i] = K[i+4];
		}
		byte[] K2 = new byte[4];
		for (int i = 0; i < 4; i++) {
			K2[i] = K[i+8];
		}
		byte[] K3 = new byte[4];
		for (int i = 0; i < 4; i++) {
			K3[i] = K[i+12];
		}
		byte[] P = s.addRoundKey(C, K3);
		P = invShiftRows(P);
		P = invShiftBox(P);
		/**
		// Raundi 1
		P =s.addRoundKey(P, K2);
		P = invMixColumns(P);
		P = invShiftRows(P);
		P = invShiftBox(P);
		
		// Raundi 2
		P = s.addRoundKey(P, K1);
		P = invMixColumns(P);
		P = invShiftRows(P);
		P = invShiftBox(P);
		s.printArray(P);
		s.printArray(k0);
	*/
		
		for(int kk = 0; kk < 2; kk++) {
			if (kk == 0) {
			P = s.addRoundKey(P, K2);
			} else if (kk == 1) {
				P = s.addRoundKey(P, K1);
			}
			
			P = invMixColumns(P);
			P = invShiftRows(P);
			P = invShiftBox(P);
		} 
		byte[] R = new byte[P.length];
		for (int i = 0; i < 4; i ++) {
			R[i] = (byte)(P[i] ^ k0[i]);
		}
		
		return R;
	}
	
	public static byte[] invShiftRows(byte[] input) {
		byte temp = input[2];
		input[2] = input[3];
		input[3] = temp;
		return input;
	}
	
	public static byte[] invMixColumns(byte[] input) {
		MixColumns m = new MixColumns();
		byte[] result = new byte[input.length];
		byte a = 15;
		byte b = 14;
		result[0] = (byte)((m.multiply(a, input[0]) ^ (m.multiply(b, input[2]))));
		result[1] = (byte)((m.multiply(a, input[1]) ^ (m.multiply(b, input[3]))));
		result[2] = (byte)((m.multiply(b, input[0]) ^ (m.multiply(b, input[2]))));
		result[3] = (byte)((m.multiply(b, input[1]) ^ (m.multiply(b, input[3]))));
		return result;
	}
	
	public static byte[] invShiftBox(byte[] input) {
		SAES s = new SAES();
		int n = input.length;
		byte[][] SBOX = {{2, 13, 6, 12}, {3, 14, 0, 4}, {9, 8, 10, 1}, {11, 15, 5, 7}};
		byte[] result = new byte[n];
		for (int i = 0; i < n; i++) {
			int[] a = s.decimalToBinary(input[i]);
			int x = a[0] * 2 + a[1];
			int y = a[2] * 2 + a[3];
			result[i] = SBOX[x][y];
		}
		return result;
	}
	public static void main(String[] args) {
		SAES s = new SAES();
		byte[] C = {15, 5, 5 ,14 };
		byte[] K3 = {9, 3, 3, 8};
		byte[] D = decryption(C);
		s.printArray(D);	
	}
}
