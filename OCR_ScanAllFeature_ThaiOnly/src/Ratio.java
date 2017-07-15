
public class Ratio {
	public static double width_Div_3;
	public static double heigh_Div_3;
	public static int width_1_in_3;
	public static int heigh_1_in_3;
	
	public static void rat(int pictureHeight , int pictureWidth , int[][] arrayPic , int countPicName ){

		double widthDiv3 = (double)pictureWidth / 3.0 ;
		double heighDiv3= (double)pictureHeight / 3.0 ;
		int width_1in3 = (int) Math.floor(widthDiv3);
		int heigh_1in3 = (int) Math.floor(heighDiv3);
		width_Div_3 = widthDiv3;
		heigh_Div_3 = heighDiv3;
		width_1_in_3 = width_1in3;
		heigh_1_in_3 = heigh_1in3;
//		System.out.println("กว้างxสูง : " + Integer.toString(width_1in3) + " x " + Integer.toString(heigh_1in3));
		
		int topLeft_White=0;/////////////////////////////////////////    topLeft บน ซ้าย
		int topLeft_Black=0;
		for(int y=0 ; y<heigh_1in3 ; y++){
			for(int x=0 ; x<width_1in3 ; x++){
				if(arrayPic[x][y]==254)
					topLeft_White++;
				else if(arrayPic[x][y]==0)
					topLeft_Black++;
			}
		}
//		System.out.println("topLeft_White : "+topLeft_White);
//		System.out.println("topLeft_Black : "+topLeft_Black);
		
		
		
		int topCenter_White=0;/////////////////////////////////////////    topCenter บน กลาง
		int topCenter_Black=0;
		for(int y=0 ; y<heigh_1in3 ; y++){
			for(int x=width_1in3 ; x< pictureWidth-width_1in3 ; x++){
				if(arrayPic[x][y]==254)
					topCenter_White++;
				else if(arrayPic[x][y]==0)
					topCenter_Black++;
			}
		}
//		System.out.println("topCenter_White : "+topCenter_White);
//		System.out.println("topCenter_Black : "+topCenter_Black);
		
		
		
		int topRight_White=0;/////////////////////////////////////////    topRight บน ขวา
		int topRight_Black=0;
		for(int y=0 ; y<heigh_1in3 ; y++) {
			for (int x = pictureWidth - width_1in3; x < pictureWidth; x++) {
				if(arrayPic[x][y]==254)
					topRight_White++;
				else if(arrayPic[x][y]==0)
					topRight_Black++;
			}
		}
//		System.out.println("topRight_White : "+topRight_White);
//		System.out.println("topRight_Black : "+topRight_Black);
		
		
		
		int midLeft_White=0;/////////////////////////////////////////    midLeft กลาง ซ้าย
		int midLeft_Black=0;
		for(int y=heigh_1in3 ; y<pictureHeight-heigh_1in3 ; y++){
			for(int x=0 ; x<width_1in3 ; x++){
				if(arrayPic[x][y]==254)
					midLeft_White++;
				else if(arrayPic[x][y]==0)
					midLeft_Black++;
			}
		}
//		System.out.println("midLeft_White : "+midLeft_White);
//		System.out.println("midLeft_Black : "+midLeft_Black);
		
		
		
		int midCenter_White=0;///////////////////////////////////////    midCenter กลาง กลาง
		int midCenter_Black=0;
		for(int y=heigh_1in3 ; y<pictureHeight-heigh_1in3 ; y++){
			for(int x=width_1in3 ; x< pictureWidth-width_1in3 ; x++){
				if(arrayPic[x][y]==254)
					midCenter_White++;
				else if(arrayPic[x][y]==0)
					midCenter_Black++;
			}
		}
//		System.out.println("midCenter_White : "+midCenter_White);
//		System.out.println("midCenter_Black : "+midCenter_Black);
		
		
		
		
		int midRight_White=0;////////////////////////////////////////    midRight กลาง ขวา
		int midRight_Black=0;
		for(int y=heigh_1in3 ; y<pictureHeight-heigh_1in3 ; y++){
			for (int x = pictureWidth - width_1in3; x < pictureWidth; x++) {
				if(arrayPic[x][y]==254)
					midRight_White++;
				else if(arrayPic[x][y]==0)
					midRight_Black++;
			}
		}
//		System.out.println("midRight_White : "+midRight_White);
//		System.out.println("midRight_Black : "+midRight_Black);
		
		
		
		
		int downLeft_White=0;////////////////////////////////////////    downLeft ล่าง ซ้าย
		int downLeft_Black=0;
		for(int y=pictureHeight-heigh_1in3 ; y<pictureHeight ; y++){
			for(int x=0 ; x<width_1in3 ; x++){
				if(arrayPic[x][y]==254)
					downLeft_White++;
				else if(arrayPic[x][y]==0)
					downLeft_Black++;
			}
		}
//		System.out.println("downLeft_White : "+downLeft_White);
//		System.out.println("downLeft_Black : "+downLeft_Black);
		
		
		
		
		int downCenter_White=0;//////////////////////////////////////    downCenter ล่าง กลาง
		int downCenter_Black=0;
		for(int y=pictureHeight-heigh_1in3 ; y<pictureHeight ; y++){
			for(int x=width_1in3 ; x< pictureWidth-width_1in3 ; x++){
				if(arrayPic[x][y]==254)
					downCenter_White++;
				else if(arrayPic[x][y]==0)
					downCenter_Black++;
			}
		}
//		System.out.println("downCenter_White : "+downCenter_White);
//		System.out.println("downCenter_Black : "+downCenter_Black);
		
		
		
		
		int downRight_White=0;///////////////////////////////////////    downRight ล่าง ขวา
		int downRight_Black=0;
		for(int y=pictureHeight-heigh_1in3 ; y<pictureHeight ; y++){
			for (int x = pictureWidth - width_1in3; x < pictureWidth; x++) {
				if(arrayPic[x][y]==254)
					downRight_White++;
				else if(arrayPic[x][y]==0)
					downRight_Black++;
			}
		}
//		System.out.println("downRight_White : "+downRight_White);
//		System.out.println("downRight_Black : "+downRight_Black);
		
		
		
		Main.arrayRatio[countPicName][0] = (double)topLeft_White /(width_1in3 * heigh_1in3) ;
		Main.arrayRatio[countPicName][1] = (double)topLeft_Black /(width_1in3 * heigh_1in3) ;
		
		Main.arrayRatio[countPicName][2] = (double)topCenter_White     /( (pictureWidth - width_1in3 - width_1in3) * heigh_1in3 );
		Main.arrayRatio[countPicName][3] = (double)topCenter_Black     /( (pictureWidth - width_1in3 - width_1in3) * heigh_1in3 );  
		Main.arrayRatio[countPicName][4] = (double)topRight_White           /(width_1in3 * heigh_1in3);
		Main.arrayRatio[countPicName][5] = (double)topRight_Black           /(width_1in3 * heigh_1in3);  
		Main.arrayRatio[countPicName][6] = (double)midLeft_White  /(width_1in3 *(pictureHeight-heigh_1in3-heigh_1in3) );
		Main.arrayRatio[countPicName][7] = (double)midLeft_Black  /(width_1in3 *(pictureHeight-heigh_1in3-heigh_1in3) );  
		Main.arrayRatio[countPicName][8] = (double)midCenter_White   /((pictureWidth - width_1in3 - width_1in3)*(pictureHeight-heigh_1in3-heigh_1in3));
		Main.arrayRatio[countPicName][9] = (double)midCenter_Black   /((pictureWidth - width_1in3 - width_1in3)*(pictureHeight-heigh_1in3-heigh_1in3));
		Main.arrayRatio[countPicName][10] = (double)midRight_White        /(width_1in3*(pictureHeight-heigh_1in3-heigh_1in3) );
		Main.arrayRatio[countPicName][11] = (double)midRight_Black        /(width_1in3*(pictureHeight-heigh_1in3-heigh_1in3) );
		Main.arrayRatio[countPicName][12] = (double)downLeft_White  /(width_1in3 * heigh_1in3) ;
		Main.arrayRatio[countPicName][13] = (double)downLeft_Black  /(width_1in3 * heigh_1in3) ;  	 
		Main.arrayRatio[countPicName][14] = (double)downCenter_White    /( (pictureWidth - width_1in3 - width_1in3) * heigh_1in3 );
		Main.arrayRatio[countPicName][15] = (double)downCenter_Black    /( (pictureWidth - width_1in3 - width_1in3) * heigh_1in3 );      
		Main.arrayRatio[countPicName][16] = (double)downRight_White			/(width_1in3 * heigh_1in3);
		Main.arrayRatio[countPicName][17] = (double)downRight_Black			/(width_1in3 * heigh_1in3);
		
		
		
//			System.out.println("\n          อัตราส่วน " + G.arrayRatio[countPicName][0] +"\n");
//				
//		System.out.println("width_1in3 * heigh_1in3                               "+ width_1in3 * heigh_1in3   );
//		System.out.println("(pictureWidth - width_1in3 - width_1in3) * heigh_1in3 "+(pictureWidth - width_1in3 - width_1in3) * heigh_1in3     );
//		System.out.println("(width_1in3 * heigh_1in3)                             "+ (width_1in3 * heigh_1in3)   );
//		System.out.println("\n(width_1in3 *(pictureHeight-heigh_1in3-heigh_1in3)                                "+ (width_1in3 *(pictureHeight-heigh_1in3-heigh_1in3) )   );
//		System.out.println("((pictureWidth - width_1in3 - width_1in3)*(pictureHeight-heigh_1in3-heigh_1in3))  "+((pictureWidth - width_1in3 - width_1in3)*(pictureHeight-heigh_1in3-heigh_1in3))    );
//		System.out.println("(width_1in3*(pictureHeight-heigh_1in3-heigh_1in3)                                 "+(width_1in3*(pictureHeight-heigh_1in3-heigh_1in3))    );
//		System.out.println("\n(width_1in3 * heigh_1in3)                                 "+(width_1in3 * heigh_1in3)    );
//		System.out.println("( (pictureWidth - width_1in3 - width_1in3) * heigh_1in3 ) "+( (pictureWidth - width_1in3 - width_1in3) * heigh_1in3 )    );
//		System.out.println("(width_1in3 * heigh_1in3)                                 "+(width_1in3 * heigh_1in3)    );

	}
}
