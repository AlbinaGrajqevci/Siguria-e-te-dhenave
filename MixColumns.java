package exercises;

public class MixColumns {
	public static byte[] mixColumns(byte[] input) {
		byte[] result = new byte[input.length];
		byte a = 1;
		byte b = 2;
		result[0] = (byte)((multiply(a, input[0]) ^ (multiply(a, input[2]))));
		result[1] = (byte)((multiply(a, input[1]) ^ (multiply(a, input[3]))));
		result[2] = (byte)((multiply(a, input[0]) ^ (multiply(b, input[2]))));
		result[3] = (byte)((multiply(a, input[1]) ^ (multiply(b, input[3]))));
		return result;
	}
	public static byte multiply(byte a, byte b) {
	    byte result = 0;
	    byte highBit = 0;
	    for (int i = 0; i < 8; i++) {
	        if ((b & 1) == 1) {
	            result ^= a;
	        }
	        highBit = (byte) (a & 0x8);
	        a <<= 1;
	        if (highBit == 0x8) {
	            a ^= 0x13; // x^4 + x + 1 = 00010011 in binary
	        }
	        b >>= 1;
	    }
	    return result;
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
	
	public static void main(String[] args) {
		SAES s = new SAES();
		byte a = 2;
		byte b = 7;
		//System.out.println(multiply(a, b));
		byte[] input = {11, 5, 2, 10};
		byte[] output = mixColumns(input);
		//s.printArray(output);
		
		byte[] input1 = {9, 15, 15, 2};
		byte[] output1 = invMixColumns(input1);
		s.printArray(output1);
	}
}	
