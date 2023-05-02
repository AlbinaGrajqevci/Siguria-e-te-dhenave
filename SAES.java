package exercises;
import java.math.*;
import java.lang.String;
import java.io.*;
import java.util.*;

public class SAES {
		public static byte[] encryption(byte[] p, byte[] k0) {
			SAES s = new SAES();
			int n = p.length;
			/**
			byte[] P = convertStringToByte(p);
			byte[] K0 = convertStringToByte(k0);
			*/
			SAESKeyGenerators k = new SAESKeyGenerators();
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
			byte[] B = new byte[n];
			byte[] D = new byte[n];
			byte[] E = new byte[n];
			for (int i = 0; i < n; i++) {
				B[i] = (byte)(p[i] ^ k0[i]);
			}
		
			byte[] C = new byte[B.length];
			for (int i = 0; i < B.length; i++) {
				C[i] = B[i];
			}
			for (int i = 0; i < 2; i++) {
				C = SBOX(C);
				C = shiftRows(C);
				C = s.MixColumns(C);
				if (i == 0) {
					C = addRoundKey(C, K1);
				} else if (i == 1) {
					C = addRoundKey(C, K2);
				}
			}
			E = SBOX(D);
			E = shiftRows(E);
			E = addRoundKey(E, K3);
			return E;
		}
	
		public byte[] MixColumns(byte[] input) {
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
		
		public static byte[] addRoundKey(byte[] D, byte[] K) {
			byte[] result = new byte[D.length];
			for (int i = 0; i < D.length; i ++) {
				result[i] = (byte) (D[i] ^ K[i]);
			}
			return result;
		}
	
		
		public static byte[] shiftRows(byte[] input) {
			byte temp = input[2];
			input[2] = input[3];
			input[3] = temp;
			return input;
		}
		
	
		public static byte[] SBOX(byte[] input) {
			int n = input.length;
			byte[][] SBOX = {{6, 11, 0, 4}, 
							{7, 14, 2, 15}, 
							{9, 8, 10, 12}, 
							{3, 1, 5, 13}};
			byte[] result = new byte[n];
			for (int i = 0; i < n; i++) {
				int[] a = decimalToBinary(input[i]);
				int x = a[0] * 2 + a[1];
				int y = a[2] * 2 + a[3];
				result[i] = SBOX[x][y];
			}
			return result;
		}
		
		   public static byte[] convertStringToByte(String[] str)
		    {
			   byte[] result = new byte[str.length];
		        // Convert string to byte
		        // using parseByte() method
			   for (int i = 0; i < str.length; i++) {
				   result[i] = Byte.parseByte(str[i], 16); 
			   }
		        return result;
		    }
		   
		   public static int[] decimalToBinary(int num)
		    {
		        // Creating and assigning binary array size
		        int[] binary = new int[4];
		        int id = 0;
		        // Number should be positive
		        while (num > 0) {
		            binary[id++] = num % 2;
		            num = num / 2;
		        }	
		        int[] result = reverse_array(binary);
		        return result;
		    }
		   
		   public static void printArray(byte[] result) {
			   for (int i = 0; i < result.length; i++) {
					 System.out.print(result[i] + " ");
				 }
			   System.out.println();
		   }
		
		   public static void printArray(int[] result) {
			   for (int i = 0; i < result.length; i++) {
					 System.out.print(result[i] + " ");
				 }
			   System.out.println();
		   }
		   

		     static int[] reverse_array(int char_array[]) 
		    { 
		    	 int n = char_array.length;
		       int[] dest_array = new int[n]; 
		       int j = n; 
		       for (int i = 0; i < n; i++) { 
		            dest_array[j - 1] = char_array[i]; 
		            j = j - 1; 
		        } 
		 
		       return dest_array;
		    } 
		 
		 public static void main(String[] args) {
			 byte[] p = {10, 2, 14, 4};
			 byte[] k0 = {12, 15, 10, 1};
			 byte[] result = encryption(p, k0);
			 printArray(result);
			 /**printArray(result);
			 byte[] C = SBOX(result);
			 printArray(C); 
			 SAES s = new SAES();
			byte[] resultt = {2, 14, 1, 7};
			byte[] D = s.MixColumns(resultt);
			printArray(D);
			byte[] DD = {12, 6, 13, 15};
			byte[] K1 = {13, 8, 7, 9};
			byte[] E = addRoundKey(DD, K1);
			printArray(E);*/
			
		 }
}


