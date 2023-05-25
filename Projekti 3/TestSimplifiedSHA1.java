package simplifedsha1;

public class TestSimplifiedSHA1 {
    public static void main(String[] ar)
    {
    String a="Hello world";
    Shendrrimet s=new Shendrrimet();
    String aa=a.replaceAll(" ","");
    String mesazhi=s.strToBinary(aa);
    SimplifiedSHA1 sa=new SimplifiedSHA1();
    String hash_vlera=sa.hash(mesazhi);
    System.out.println(a+"    -------->   "+hash_vlera);
    }
}
