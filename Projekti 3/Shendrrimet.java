package simplifedsha1;

public class Shendrrimet {
    int value;
    public String strToBinary(String s)
    {
        int n = s.length();
        String bin="";
        String text="";
        for (int i = 0; i < n; i++)
        {
            // convert each char to
            // ASCII value
            int val1 = Integer.valueOf(s.charAt(i));
 
            // Convert ASCII value to binary
            
            int val=val1;

            while (val > 0)
            {   
                if (val % 2 == 1)
                {
                    bin += '1';
                }
                else
                    bin += '0';
                val /= 2;
            }
            
            bin = reverse(bin);
            if(val1<128)
            {bin="0"+bin;
            if(val1<64)
            {bin="0"+bin;
            if(val1<20)
            {bin="0"+bin;}}}
            text=text+bin;
            bin="";
            
           
        }
        
        return text;
    }
   
    public int int_value(String s)
    {   int n = s.length();
         for (int i = 0; i < n; i++)
        {
            // convert each char to
            // ASCII value
            int val = Integer.valueOf(s.charAt(i));
            value=value+val;}
        return value;
    }
 
    static String reverse(String input)
    {
        char[] a = input.toCharArray();
        int l, r = 0;
        r = a.length - 1;
 
        for (l = 0; l < r; l++, r--)
        {
            // Swap values of l and r
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }
  
    public  char bin_to_hex(boolean [] bin) { 
        char N='0';
        int[] bin1 = new int[bin.length];
        for (int i = 0; i < bin.length; i++) {
            if (bin[i]) {
                bin1[i] = 1;
            } else {
                bin1[i] = 0;
            }
        }

        int n = bin1[0] * 8 + bin1[1] * 4 + bin1[2] * 2 + bin1[3] * 1;

        if(n==0)
        {N='0';}
        if(n==1)
        {N='1';}
        if(n==2)
        {N='2';}
        if(n==3)
        {N='3';}
        if(n==4)
        {N='4';}
        if(n==5)
        {N='5';}
        if(n==6)
        {N='6';}
        if(n==7)
        {N='7';}
        if(n==8)
        {N='8';}
        if(n==9)
        {N='9';}
        if(n==10)
        {N='A';}
        if(n==11)
        {N='B';}
        if(n==12)
        {N='C';}
        if(n==13)
        {N='D';}
        if(n==14)
        {N='E';}
        if(n==15)
        {N='F';}
        
        return N;

    }
   
    public boolean [] hex_to_bin(char a) {
        boolean rez []=new boolean[4];
        if(a=='1')
        {rez[3]=true;} //0001
        if(a=='2')
        {rez[2]=true;} //0010
        if(a=='3')
        { rez[2]=rez[3]=true;} //0011
        if(a=='4')
        {rez[1]=true;} //0100
        if(a=='5')
        {rez[1]=rez[3]=true;} //0101
        if(a=='6')
        {rez[1]=rez[2]=true;} //0110
        if(a=='7')
        {rez[1]=rez[2]=rez[3]=true;} //0111
        if(a=='8')
        {rez[0]=true;} //1000
        if(a=='9')
        {rez[0]=rez[3]=true;} //1001
        if(a=='A')
        {rez[0]=rez[2]=true;} //1010
        if(a=='B')
        {rez[0]=rez[2]=rez[3]=true;} //1011
        if(a=='C')
        {rez[0]=rez[1]=true;} //1100
        if(a=='D')
        {rez[0]=rez[1]=rez[3]=true;} //1101
        if(a=='E')
        {rez[0]=rez[1]=rez[2]=true;} //1110
        if(a=='F')
        {rez[0]=rez[1]=rez[2]=rez[3]=true;} //1111
        return rez;
    }

    public boolean[] hex_bin(char a, char b)
    {  ;
        // A0 dhe A1 e perbejne A (dy vlera heksadecimale psh. A=4F)
        boolean[] A0=hex_to_bin(a);
        boolean[] A1=hex_to_bin(b);
        boolean[] A=new boolean[8];
        for(int i=0; i<4; i++)
        {   A[i]=A0[i];
            A[i+4]=A1[i];  }
        
        return A;
    }
   
    public char[] bin_hex(boolean[] a)
    {   
        boolean[] a0=new boolean[4];
        boolean[] a1=new boolean[4];
        for(int k=0; k<4; k++)
        {   a0[k]=a[k];
            a1[k]=a[k+4]; }
        char[] hex=new char[2];
        hex[0]=bin_to_hex(a0);
        hex[1]=bin_to_hex(a1);

        return hex;
        
    }

    public String bin_to_hex(String a)
    {   boolean[] b=new boolean[a.length()];
        for(int i=0; i<b.length; i++)
        {  if(a.charAt(i)=='1')
             {b[i]=true;}
        }
        int n=b.length/4;
        char[] hex=new char[n];
        boolean[] temp=new boolean[4];
        
        for(int i=0;i<n; i++)
        {
            for(int j=0; j<4; j++)
            {
                temp[j]=b[4*i+j];
            }
            hex[i]=bin_to_hex(temp);
        }
        String rez="";
        for(int i=0; i<hex.length; i++)
        {
            rez=rez+hex[i];
        }

        return rez;

    }

    public String int_to_bin(int val)
    {
        String bin="";
        
        while (val > 0)
        {   
            if (val % 2 == 1)
            {
                bin += '1';
            }
            else
                bin += '0';
            val /= 2;
        }
        String rez="";
        if(bin.length()<16)
        {   for(int i=0; i<16-bin.length(); i++)
            {rez="0"+rez;}
        }
        for(int i=bin.length(); i>0; i--)
        {
            rez=rez+bin.charAt(i-1);
        }
        
        return rez;
    }
 
    public String boolean_string(boolean[] b)

    {
        String a="";
        for(int i=0; i<b.length; i++)
        {
            if(b[i])
            {
                a=a+"1";
            }
            else{a=a+"0";}
        }
        return a;
    }

    public boolean[] hex_boolean(String hex)
    {
        char [] a=new char[8];
        for(int i=0; i<a.length; i++)
        {
            a[i]=hex.charAt(i);
        }

        boolean [] rez1=hex_to_bin(a[0]);
        boolean [] rez2=hex_to_bin(a[1]);
        boolean [] rez3=hex_to_bin(a[2]);
        boolean [] rez4=hex_to_bin(a[3]);
        boolean [] rez5=hex_to_bin(a[4]);
        boolean [] rez6=hex_to_bin(a[5]);
        boolean [] rez7=hex_to_bin(a[6]);
        boolean [] rez8=hex_to_bin(a[7]);

        boolean[] rez=new boolean[32];
        for(int i=0; i<4; i++)
        {
            rez[i]=rez1[i];
            rez[i+4]=rez2[i];
            rez[i+8]=rez3[i];
            rez[i+12]=rez4[i];
            rez[i+16]=rez5[i];
            rez[i+20]=rez6[i];
            rez[i+24]=rez7[i];
            rez[i+28]=rez8[i];
        }

        return rez;
    }
   
    public String hex_binary(String hex)
    {   
         char [] a=new char[8];
        for(int i=0; i<a.length; i++)
        {
            a[i]=hex.charAt(i);
        }

        boolean [] rez1=hex_to_bin(a[0]);
        boolean [] rez2=hex_to_bin(a[1]);
        boolean [] rez3=hex_to_bin(a[2]);
        boolean [] rez4=hex_to_bin(a[3]);
        boolean [] rez5=hex_to_bin(a[4]);
        boolean [] rez6=hex_to_bin(a[5]);
        boolean [] rez7=hex_to_bin(a[6]);
        boolean [] rez8=hex_to_bin(a[7]);

        boolean[] rez=new boolean[32];
        for(int i=0; i<4; i++)
        {
            rez[i]=rez1[i];
            rez[i+4]=rez2[i];
            rez[i+8]=rez3[i];
            rez[i+12]=rez4[i];
            rez[i+16]=rez5[i];
            rez[i+20]=rez6[i];
            rez[i+24]=rez7[i];
            rez[i+28]=rez8[i];
        }
        String bin="";
        for(int i=0; i<rez.length; i++)
        {
            if(rez[i])
            {bin=bin+"1";}
            else{bin=bin+"0";}
        }
        
        return bin;
    }
    
    public boolean[] string_nr_to_boolean(String a)
    {
        int aa=Integer.parseInt(a);
        boolean[] bin=new boolean[16];
        for(int i=0; i<16; i++)
        {
            if(aa%2==1)
            {bin[16-i-1]=true;}
            aa=aa/2;
        }
        return bin;
    }

    public String int_boolean(String a)
    {
        int aa=Integer.parseInt(a);
        boolean[] bin=new boolean[16];
        for(int i=0; i<16; i++)
        {
            if(aa%2==1)
            {bin[16-i-1]=true;}
            aa=aa/2;
        }
        String bin1="";
        for(int i=0; i<bin.length; i++)
        {
            if(bin[i])
            {bin1=bin1+"1";}
            else{bin1=bin1+"0";}
        }
        return bin1;
    }

    public String boolean_to_integer_string(String c)
    {   boolean[] a=new boolean[c.length()];
        for(int i=0; i<a.length; i++)
        {  if(c.charAt(i)=='1')
           {a[i]=true;}

        }
        double b=0;
        for(int i=0; i<a.length; i++)
        {
            if(a[i])
            {
                b=b+Math.pow(2,a.length-i-1);
            }
        }
        int bb=(int)b;
        String rez=""+bb;
        return rez;
    }

    public String hex_to_dec(String a)
    {
       int num=Integer.parseInt(a,16);
       String b=""+num;
       return b;
    }

    public String dec_to_bin(String a)
    {
        int num=Integer.parseInt(a);
        String bin="";
        while(num!=0){
            if(num%2!=0)
            {bin+="1";}
            else{bin+="0";}
            num=num/2;
        }
        String bin2="";
        for(int i=0; i<bin.length(); i++)
        {
           bin2=bin2+bin.charAt(bin.length()-1-i);
        }

        return bin2;
    }
}
