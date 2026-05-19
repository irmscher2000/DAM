package prog02_ejerc12;
public class PROG02_Ejerc12 {
    public static void main(String[] args) {
        
        // Declaro el tipo de variable con su valor.
        float x = 4.5f;
        float y = 3.0f;
        int i = 2;
        int j = (int)(i * x); // El resultado como int.
        double dx = 2.0;
        double dz = dx * y;
        
        // Imprimo por pantalla
        System.out.println("------- Conversiones entre enteros y coma flotante -------");
        System.out.println("Producto de int por float: j = i * x " + j);
        System.out.println("Producto de float por double: dz = dx * y = " + dz);
        
        byte bx = -128;
        byte by = 1;
        byte bz = (byte) (bx - by);  // El resultado se fuerza con byte.
        j =  bx - by; // Sobrescribo el valor de jota sin utilizar int que lo vuelve a declarar.
        
        // Imprimo por pantalla
        System.out.println("\n------- Operaciones con byte -------");
        System.out.println("byte - 128 - 1 = "+ bz);
        System.out.println("(int)(- 128 - 1)= " + j);
                
        System.out.println("\n------- Operaciones con short -------");
        short sx = 32767;
        short sy = 1;
        short sz = (short) (sx + sy);  // El resultado se fuerza a short. 
        
        // Imprimo por pantalla
        System.out.println("32767 + 1 = " + sz);
        
        char cx = '\u001F';
        char cy = '\u0002';
        int z =  (cx + cy);
        int w =  cx + 2;
        
        //Imprimo por pantalla
        System.out.println("\n------- Operaciones con char -------");
        System.out.println("char: Z = " + z );
        System.out.println("char (0x001F) + 2 + = " + w);   
    }
}
