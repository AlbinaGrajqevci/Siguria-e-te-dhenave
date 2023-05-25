package simplifedsha1;

public class SSHA13c {
    public static void main(String[] args){
        // Mesazhi i dhene
        String m="11101010110001101010110010101";
        // Hash vlera
        SimplifiedSHA1 s=new SimplifiedSHA1();
        String hm=s.hash(m);

          String try_hash="";
          int gjatesia_stringut=1;
          String start_search="0";
          int search=(int)(Math.pow(2,gjatesia_stringut));
          boolean v=true;
          
          while(v)
          { for(int i=0; i<search; i++)
              {try_hash=add(start_search,i);
                  System.out.println("Hash vlera per stringun ="+try_hash+" eshte ="+s.hash(try_hash));
               if(s.hash(try_hash).compareTo(hm)==0)
               {  System.out.println("Vlera e kerkuar:"+ try_hash);
                   v=false;}}
               start_search+="0";
               gjatesia_stringut+=1;
              
               search=(int)(Math.pow(2,gjatesia_stringut));
          
          }
   
      }

      public static String add(String a,int b)
      {   Shendrrimet s=new Shendrrimet();
          String aa=s.boolean_to_integer_string(a);
          int a2=Integer.parseInt(aa);
          a2=a2+b;
          String aa2=""+a2;
          String bb=s.dec_to_bin(aa2);
          if(bb.length()!=a.length())
          {    int zero=a.length()-bb.length();
              for(int i=0; i<zero; i++)
              {
                  bb="0"+bb;
              }
          }
          return bb;
      }
}
