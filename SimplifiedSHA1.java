package simplifedsha1;

public class SimplifiedSHA1 {
	    static boolean[] W0,W1,W2,W3,W4,W5,W6,W7,W8,W9,W10,W11,W12,W13,W14,W15;
	  
	    public String hash(String h)
	    {   String vektori_inicializues="45AFACFE"; // A=45; B=AF; C=CF; D=FE;
	        String hash_vlera="";
	        String hash1=paddedString(h);
	        int r=hash1.length()/8;
	        for(int i=0; i<r; i++)
	        {
	            hash_vlera=H(hash1.substring(i*8, i*8+8),vektori_inicializues);
	            vektori_inicializues=hash_vlera;
	        }
	        return hash_vlera;
	    }
	    
	    public  String paddedString(String mesazhi)
	    {   String padded_string="";   
	        int l=mesazhi.length();
	        int k=32-16-(l+1);
	        while(k<0)
	        {
	            k=32+k;
	        }
	        k=k%32;
	        padded_string=mesazhi+"1";
	        for(int i=0; i<k; i++)
	        {padded_string=padded_string+"0";}
	        Shendrrimet s=new Shendrrimet();
	        padded_string=padded_string+s.int_to_bin(l);
	        
	        // shendrrimi i mesazhit ne hex
	         padded_string=s.bin_to_hex(padded_string);
	        return padded_string;

	    } 
	    
	    public static String H(String a, String iv)
	    {   boolean[] x_i=String_bin(a);
	        kalkulimi_Ws(x_i);
	        char[] ABCD=new char[8]; 
	        for(int i=0; i<ABCD.length; i++)
	        {
	            ABCD[i]=iv.charAt(i);
	        }
	        Shendrrimet s=new Shendrrimet();
	        boolean[] K1=s.hex_bin('5','A');// K1=5A
	        boolean[] K2=s.hex_bin('E','7');// K2=E7
	        boolean[] K3=s.hex_bin('8','C');// K3=8C
	        boolean[] K4=s.hex_bin('B','D');// K4=BD
	        char[] etapa1=etapa(1,W0,W1,W2,W3,ABCD,K1); // parametri i pare tregon funksionin f1
	        char[] etapa2=etapa(2,W4,W5,W6,W7,etapa1,K2);
	        char[] etapa3=etapa(3,W8,W9,W10,W11,etapa2,K3);
	        char[] etapa4=etapa(4,W12,W13,W14,W15,etapa3,K4);
	        
	        boolean[] A_fillim=s.hex_bin(ABCD[0],ABCD[1]);
	        boolean[] B_fillim=s.hex_bin(ABCD[2],ABCD[3]);
	        boolean[] C_fillim=s.hex_bin(ABCD[4],ABCD[5]);
	        boolean[] D_fillim=s.hex_bin(ABCD[6],ABCD[7]);

	        boolean[] A_fund=s.hex_bin(etapa4[0],etapa4[1]);
	        boolean[] B_fund=s.hex_bin(etapa4[2],etapa4[3]);
	        boolean[] C_fund=s.hex_bin(etapa4[4],etapa4[5]);
	        boolean[] D_fund=s.hex_bin(etapa4[6],etapa4[7]);

	        char[] Am=s.bin_hex(mbledhja_mod(A_fillim,A_fund));
	        char[] Bm=s.bin_hex(mbledhja_mod(B_fillim,B_fund));
	        char[] Cm=s.bin_hex(mbledhja_mod(C_fillim,C_fund));
	        char[] Dm=s.bin_hex(mbledhja_mod(D_fillim,D_fund));

	        char[] hash=new char[8];
	        hash[0]=Am[0];
	        hash[1]=Am[1];
	        hash[2]=Bm[0];
	        hash[3]=Bm[1];
	        hash[4]=Cm[0];
	        hash[5]=Cm[1];
	        hash[6]=Dm[0];
	        hash[7]=Dm[1];

	        String enkriptimi="";
	        for(int i=0; i<8; i++)
	           {enkriptimi=enkriptimi+hash[i];}

	        return enkriptimi;
	    }
	   
	    public static char[] etapa(int funksioni_i_n,boolean[] w_1, boolean[] w_2, boolean[] w_3, boolean[] w_4, char[] vlerat_fillestare, boolean[] K )
	    {
	        char[] etapa_n=new char[8];
	        etapa_n=roundi(vlerat_fillestare,w_1,K,funksioni_i_n);
	        etapa_n=roundi(etapa_n,w_2,K,funksioni_i_n);
	        etapa_n=roundi(etapa_n,w_3,K,funksioni_i_n);
	        etapa_n=roundi(etapa_n,w_4,K,funksioni_i_n);
	        return etapa_n;
	    }

	    public static char [] roundi (char[] a,boolean[] w, boolean[] K, int funksioni_i_n)
	    {
	        /* Sqarim: per shkak te implementimit te klases Shendrrimet input merret varg i karaktereve ashtu qe
	        A=a[0]a[1], pra secili nr heksadecimal(paraqitet me a[i]). Meqe nga skema A eshte 8 bit atehere merr dy karaktere 
	        nga vargu. Kjo metode kthen varg te karaktereve me gjatesi 8 (nga dy per secilen:A,B,C,D). Punohet me vargje 
	        booleane */
	        Shendrrimet s=new Shendrrimet();
	        boolean [] A=s.hex_bin(a[0],a[1]);
	        boolean [] B=s.hex_bin(a[2],a[3]);
	        boolean [] C=s.hex_bin(a[4],a[5]);
	        boolean [] D=s.hex_bin(a[6],a[7]);
	        
	        // Vlerat direkte A->B, C->D
	        char[] B_1=s.bin_hex(A);
	        char[] D_1=s.bin_hex(C);

	        // Vlera B->C por pas shift B<<<7(mund te merret edhe si >>>1)
	        boolean[] B_shift=new boolean[8];
	        for(int i=1; i<8; i++)
	        {  B_shift[i]=B[i-1];}
	           B_shift[0]=B[7];
	        char[] C_1=s.bin_hex(B_shift);

	        // Llogaritja e A 
	        boolean [] A_shift=new boolean[8];  // shift i A <<<3
	        for(int i=0; i<5; i++)
	        {  A_shift[i]=A[i+3];}
	           A_shift[5]=A[0];
	           A_shift[6]=A[1]; 
	           A_shift[7]=A[2];
	        boolean[] llogaritja_A=new boolean[8];
	        llogaritja_A=mbledhja_mod(D,f(B,C,funksioni_i_n));
	        llogaritja_A=mbledhja_mod(llogaritja_A,A_shift);
	        llogaritja_A=mbledhja_mod(llogaritja_A,w);
	        llogaritja_A=mbledhja_mod(llogaritja_A,K);

	        char[] A_1=s.bin_hex(llogaritja_A);   
	           
	        
	        // nga dy karaktere paraqesin dy vlerat heksadecimale per A,B,C,D
	        char[] A_B_C_D=new char[8];
	        A_B_C_D[0]=A_1[0];
	        A_B_C_D[1]=A_1[1];
	        A_B_C_D[2]=B_1[0];
	        A_B_C_D[3]=B_1[1];
	        A_B_C_D[4]=C_1[0];
	        A_B_C_D[5]=C_1[1];
	        A_B_C_D[6]=D_1[0];
	        A_B_C_D[7]=D_1[1];
	      

	       return A_B_C_D;
	    }
	    
	    private static void kalkulimi_Ws(boolean[] x_i)
	    { 
	        // ndarja e mesazhit x_i ne kater pjese x_i_j
	        boolean[] x_i_0=new boolean[8];
	        boolean[] x_i_1=new boolean[8];
	        boolean[] x_i_2=new boolean[8];
	        boolean[] x_i_3=new boolean[8];
	        // mbushja e x_i_j
	        for(int k=0; k<8; k++)
	        {    x_i_0[k]=x_i[k];
	             x_i_1[k]=x_i[k+8];
	             x_i_2[k]=x_i[k+16];
	             x_i_3[k]=x_i[k+24];   }
	        // mbushja e W0-W3 direkte nga x_i_j
	        W0=x_i_0;
	        W1=x_i_1;
	        W2=x_i_2;
	        W3=x_i_3;
	       
	        // mbushja e W4-16 ashtu qe (Wj-4 XOR Wj-2)<<<2
	        W4=w_xor_shift(W0, W2);
	        W5=w_xor_shift(W1, W3);
	        W6=w_xor_shift(W2, W4);
	        W7=w_xor_shift(W3, W5);
	        W8=w_xor_shift(W4, W6);
	        W9=w_xor_shift(W5, W7);
	        W10=w_xor_shift(W6, W8);
	        W11=w_xor_shift(W7, W9);
	        W12=w_xor_shift(W8, W10);
	        W13=w_xor_shift(W9, W11);
	        W14=w_xor_shift(W10, W12);
	        W15=w_xor_shift(W11, W13);

	    }  

	    public static boolean [] w_xor_shift(boolean[] w1, boolean[] w2)
	    {       boolean[] w1_xor_shift_w2=new boolean[8];
	            boolean[] w1_XOR_w2=new boolean[8];
	            for(int k=0; k<8; k++)
	            {  w1_XOR_w2[k]=w1[k]^w2[k];}
	            w1_xor_shift_w2[6]=w1_XOR_w2[0]; 
	            w1_xor_shift_w2[7]=w1_XOR_w2[1];
	            for(int k=0; k<6; k++)
	            { w1_xor_shift_w2[k]=w1_XOR_w2[k+2]; }
	            
	            return w1_xor_shift_w2;
	    }

	    public static boolean[] f(boolean[] B,boolean[] C,int funksioni_i_n)
	    {   boolean[] funksioni=new boolean[8];
	        if(funksioni_i_n==1)
	        {    for(int i=0; i<8; i++)
	                { funksioni[i]=B[i]&&C[i]; }}
	                
	                if(funksioni_i_n==2|| funksioni_i_n==4)
	                {    for(int i=0; i<8; i++)
	                        { funksioni[i]=B[i]^C[i]; }}

	                        if(funksioni_i_n==3)
	                           {    for(int i=0; i<8; i++)
	                                   { funksioni[i]=B[i]^(!C[i]); }}
	        
	        return funksioni;
	    }

	    public static boolean[] mbledhja_mod(boolean[] a, boolean[] b)
	    {
	        int aa=0;
	        int bb=0;
	        for(int k=0; k<8; k++)
	        {  if(a[k])
	            {aa=aa+(int)(Math.pow(2,7-k));}
	           if(b[k])
	            {bb=bb+(int)(Math.pow(2,7-k));}}
	        int c=aa+bb;
	        c=c%(int)(Math.pow(2,8));
	     
	        boolean[] mbetja=new boolean[8];
	        for(int i=0; i<8; i++)
	        {   if(c%2==1)
	               {mbetja[7-i]=true;}
	            c=c/2;}

	        return mbetja;
	    }
	   
	    public static boolean[] String_bin(String a)
	    {   boolean[] xi=new boolean[32];
	        Shendrrimet s=new Shendrrimet();
	        boolean[] x0=s.hex_bin(a.charAt(0),a.charAt(1));
	        boolean[] x1=s.hex_bin(a.charAt(2),a.charAt(3));
	        boolean[] x2=s.hex_bin(a.charAt(4),a.charAt(5));
	        boolean[] x3=s.hex_bin(a.charAt(6),a.charAt(7));

	        for(int i=0; i<8; i++)
	        {   xi[i]=x0[i];
	            xi[i+8]=x1[i];
	            xi[i+16]=x2[i];
	            xi[i+24]=x3[i]; }
	      
	        return xi;
	    }
	    
	
}
