import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;

public class Main {
//	private static int beginOfPicture = 1; // รูปเริ่มต้น
//	private static int endOfPicture = 10; // รูปสุดท้าย

	protected static double[][] array200300400=new double[3][18];
	
	protected static StringBuffer strBuff = new StringBuffer();
	protected static double[][] arrayRatio;

//	protected static StringBuffer featureBuffer = new StringBuffer();
	protected static double[][] arrayFeature = new double[76][18]; //ของ Thai
	protected static int rowFeature=0;
	protected static int folderThai=0;
	
	public static void main(String[] args) {

		GregorianCalendar t1 = new GregorianCalendar();
		System.out.println("\nเริ่ม  " + t1.get(Calendar.HOUR) + ":"+ t1.get(Calendar.MINUTE) + ":" + t1.get(Calendar.SECOND) + ":"+ t1.get(Calendar.MILLISECOND) + "\n");

		for(int r=0 ; r<76 ; r++){
			for(int c=0 ; c<18 ; c++)
				arrayFeature[r][c]=0.0;
		}
		
//		for(folderThai = 161 ; folderThai<=200 ; folderThai++ ){
		for(folderThai = 201 ; folderThai<=237 ; folderThai++ ){
			try{
				scanOneChar();
			}catch(Exception a){
				System.out.println("ไม่เจอโฟลเดอร์นี้ Thai/"+folderThai+"   ");
			}
			rowFeature++;
		}
		
		GregorianCalendar t2 = new GregorianCalendar();
		System.out.println("\nเสร็จแล้ว " + t2.get(Calendar.HOUR) + ":"	+ t2.get(Calendar.MINUTE) + ":" + t2.get(Calendar.SECOND) + ":"	+ t2.get(Calendar.MILLISECOND) + "\n");

		int t2hour = t2.get(Calendar.HOUR);
		int t2minute = t2.get(Calendar.MINUTE);
		int t2second = t2.get(Calendar.SECOND);
		int t2mili = t2.get(Calendar.MILLISECOND);

		int t1hour = t1.get(Calendar.HOUR);
		int t1minute = t1.get(Calendar.MINUTE);
		int t1second = t1.get(Calendar.SECOND);
		int t1mili = t1.get(Calendar.MILLISECOND);
		System.out.println("ใช้เวลา " + (t2hour - t1hour) + ":"	+ (t2minute - t1minute) + ":" + (t2second - t1second) + ":"	+ (t2mili - t1mili));
		
		WriteTXT.lineByLine("\n\n ======================================================================================= \n\n");

	}//end main

	

	private static void scanOneChar() {

		int row234=0;
		
		//---------------------------------------------------------------------------------start
		
		//start for 200 300 400
		for(int subFolder=200 ; subFolder<=400 ; subFolder=subFolder+100){//
		
			// ณ ที่โฟลเดอร์ normal
			String nameOfPicture = null;
//			String nameOfDirectory = "/Users/A/Documents/Project_Data/Thai/"+ Integer.toString(folderThai) +"/"+Integer.toString(subFolder)+"/normal/";
			String nameOfDirectory = "/Users/A/Google Drive/ProjectEclipseJava/OCR_DataThai/"+ Integer.toString(folderThai) +"/"+Integer.toString(subFolder)+"/normal/";
			
			int beginOfPicture = 1;
			int endOfPicture = new File(nameOfDirectory).listFiles().length; 
			
			arrayRatio = new double[endOfPicture + 1][18];
			for (int y = 0; y < endOfPicture; y++) {
				for (int x = 0; x <= 17; x++) {
					arrayRatio[y][x] = 0; // เซตให้ Array เป็น 0 
				}
			}
			
			//start scan 1 folder
			for (int countPicName = beginOfPicture; countPicName < endOfPicture; countPicName++) {//ลูปนี้จะ scan ในหนึ่ง folder
				try {
					
					if (countPicName <= 9)
						nameOfPicture = "A_000".concat(Integer.toString(countPicName) + ".bmp");
					else if (countPicName <= 99)
						nameOfPicture = "A_00".concat(Integer.toString(countPicName) + ".bmp");
					else if (countPicName <= 999)
						nameOfPicture = "A_0".concat(Integer.toString(countPicName) + ".bmp");
					else if (countPicName <= 9999)
						nameOfPicture = "A_".concat(Integer.toString(countPicName) + ".bmp");
					else
						System.out.println("\n\n มีชื่อรูปไม่ตรงตามที่กำหนด   ที่ตำแหน่ง  "+ countPicName + "\n\n");
					String pathOfPicture = nameOfDirectory.concat(nameOfPicture);								//featureFile.append("\n"+   Integer.toString(folderThai) +"/"+Integer.toString(subFolder)+"/normal/"+nameOfPicture );//\\/\\//\\//\\
					

					BufferedImage picture = ImageIO.read(new File(pathOfPicture));
					int pictureWidth = picture.getWidth();
					int pictureHeight = picture.getHeight();
					
					if( (pictureWidth<3) || (pictureHeight<3))   						 									//ข้ามไปครับ		System.out.println("     " + pathOfPicture);
						continue;
					
					int[][] arrayLum = new int[pictureWidth][pictureHeight];
					int[][] arrayPic = new int[pictureWidth][pictureHeight];

					//Manual manipulation...
					for (int x = 0; x < pictureWidth; x++) {
						for (int y = 0; y < pictureHeight; y++) {
							Color color = new Color(picture.getRGB(x, y));

							int pixel = color.getRGB();

							int red = (pixel >> 16) & 255;
							int green = (pixel >> 8) & 255;
							int blue = (pixel) & 255;

							final int lum = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
							arrayLum[x][y] = lum;

							red = (lum << 16);
							green = (lum << 8);
							blue = lum;

							pixel = red + green + blue;
							picture.setRGB(x, y, pixel);
						}
					}// end for
					// จัดเรียงตำแหน่ง ให้ตรงตาม แกน x , y
					for (int y = 0; y < pictureHeight; y++) {
						for (int x = 0; x < pictureWidth; x++) {
							if (arrayLum[x][y] <= 9)
								strBuff.append("  "	+ Integer.toString(arrayLum[x][y]) + " ");
							else if (arrayLum[x][y] <= 99)
								strBuff.append(" " + Integer.toString(arrayLum[x][y])	+ " ");
							else
								strBuff.append(Integer.toString(arrayLum[x][y]) + " ");
							strBuff.append(",");
							arrayPic[x][y] = arrayLum[x][y];
						}
						strBuff.append("\n");
					}
					Ratio.rat(pictureHeight, pictureWidth, arrayPic, countPicName);
				} 
				catch (Exception ex) {
//					featureBuffer.append("     						"+ex);
				}
			}// end scan 1 folder
			WriteTXT.lineByLine("\n");
			
			double averageTopLeft_White = 0.0;
			double averageTopLeft_Black = 0;
			double averageTopCenter_White = 0;
			double averageTopCenter_Black = 0;
			double averageTopRight_White = 0;
			double averageTopRight_Black = 0;
			double averageMidLeft_White = 0;
			double averageMidLeft_Black = 0;
			double averageMidCenter_White = 0;
			double averageMidCenter_Black = 0;
			double averageMidRight_White = 0;
			double averageMidRight_Black = 0;
			double averageDownLeft_White = 0;
			double averageDownLeft_Black = 0;
			double averageDownCenter_White = 0;
			double averageDownCenter_Black = 0;
			double averageDownRight_White = 0;
			double averageDownRight_Black = 0;

			// System.out.println("\n\naa " +arrayRatio[1][0]);
			// System.out.println ("bb " +arrayRatio[2][0]);
			// System.out.println ("cc " +arrayRatio[3][0]);

			for (int y = beginOfPicture; y <= endOfPicture; y++) {
				averageTopLeft_White += arrayRatio[y][0];
				averageTopLeft_Black += arrayRatio[y][1];
				averageTopCenter_White += arrayRatio[y][2];
				averageTopCenter_Black += arrayRatio[y][3];
				averageTopRight_White += arrayRatio[y][4];
				averageTopRight_Black += arrayRatio[y][5];
				averageMidLeft_White += arrayRatio[y][6];
				averageMidLeft_Black += arrayRatio[y][7];
				averageMidCenter_White += arrayRatio[y][8];
				averageMidCenter_Black += arrayRatio[y][9];
				averageMidRight_White += arrayRatio[y][10];
				averageMidRight_Black += arrayRatio[y][11];
				averageDownLeft_White += arrayRatio[y][12];
				averageDownLeft_Black += arrayRatio[y][13];
				averageDownCenter_White += arrayRatio[y][14];
				averageDownCenter_Black += arrayRatio[y][15];
				averageDownRight_White += arrayRatio[y][16];
				averageDownRight_Black += arrayRatio[y][17];
			}
			
			array200300400[row234][0] = averageTopLeft_White/ (endOfPicture - beginOfPicture + 1);//averageTopLeft_White
			array200300400[row234][1] = averageTopLeft_Black/ (endOfPicture - beginOfPicture + 1);//averageTopLeft_Black
			
			array200300400[row234][2] =averageTopCenter_White/ (endOfPicture - beginOfPicture + 1);//averageTopCenter_White
			array200300400[row234][3] =averageTopCenter_Black/ (endOfPicture - beginOfPicture + 1);//averageTopCenter_Black
			
			array200300400[row234][4] = averageTopRight_White/ (endOfPicture - beginOfPicture + 1);//averageTopRight_White
			array200300400[row234][5] = averageTopRight_Black/ (endOfPicture - beginOfPicture + 1);//averageTopRight_Black
			
			array200300400[row234][6] =averageMidLeft_White/ (endOfPicture - beginOfPicture + 1);//averageMidLeft_White
			array200300400[row234][7] =averageMidLeft_Black/ (endOfPicture - beginOfPicture + 1);//averageMidLeft_Black
			
			array200300400[row234][8] = averageMidCenter_White/ (endOfPicture - beginOfPicture + 1);//averageMidCenter_White
			array200300400[row234][9] = averageMidCenter_Black/ (endOfPicture - beginOfPicture + 1);//averageMidCenter_Black
			
			array200300400[row234][10] = averageMidRight_White/ (endOfPicture - beginOfPicture + 1);//averageMidRight_White
			array200300400[row234][11] = averageMidRight_Black/ (endOfPicture - beginOfPicture + 1);//averageMidRight_Black
			
			array200300400[row234][12] = averageDownLeft_White/ (endOfPicture - beginOfPicture + 1);//averageDownLeft_White
			array200300400[row234][13] = averageDownLeft_Black/ (endOfPicture - beginOfPicture + 1);//averageDownLeft_Black
			
			array200300400[row234][14] = averageDownCenter_White/ (endOfPicture - beginOfPicture + 1);//averageDownCenter_White
			array200300400[row234][15] = averageDownCenter_Black/ (endOfPicture - beginOfPicture + 1);//averageDownCenter_Black
			
			array200300400[row234][16] = averageDownRight_White/ (endOfPicture - beginOfPicture + 1);//averageDownRight_White
			array200300400[row234][17] = averageDownRight_Black/ (endOfPicture - beginOfPicture + 1);//averageDownRight_Black
			
			row234++;
			
		}//end for 200 300 400
		
		
		//แล้วหาค่าเฉลี่ยของ 200 300 400
		for(int r=0; r<row234 ;r++){
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][0] +=array200300400[r][0] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][1] +=array200300400[r][1] ));  
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][2] +=array200300400[r][2] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][3] +=array200300400[r][3] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][4] +=array200300400[r][4] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][5] +=array200300400[r][5] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][6] +=array200300400[r][6] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][7] +=array200300400[r][7] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][8] +=array200300400[r][8] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][9] +=array200300400[r][9] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][10] +=array200300400[r][10] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][11] +=array200300400[r][11] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][12] +=array200300400[r][12] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][13] +=array200300400[r][13] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][14] +=array200300400[r][14] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][15] +=array200300400[r][15] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][16] +=array200300400[r][16] ));
//			System.out.println( ">> "+r +"   "+  Double.toString( arrayFeature[rowFeature][17] +=array200300400[r][17] ));
			
			
			
			arrayFeature[rowFeature][0] += array200300400[r][0] ;//averageTopLeft_White
			arrayFeature[rowFeature][1] += array200300400[r][1] ;//averageTopLeft_Black
			
			arrayFeature[rowFeature][2] += array200300400[r][2] ;//averageTopCenter_White
			arrayFeature[rowFeature][3] += array200300400[r][3] ;//averageTopCenter_Black
			
			arrayFeature[rowFeature][4] += array200300400[r][4] ;//averageTopRight_White
			arrayFeature[rowFeature][5] += array200300400[r][5] ;//averageTopRight_Black
			
			arrayFeature[rowFeature][6] += array200300400[r][6] ;//averageMidLeft_White
			arrayFeature[rowFeature][7] += array200300400[r][7] ;//averageMidLeft_Black
			
			arrayFeature[rowFeature][8] += array200300400[r][8] ;//averageMidCenter_White
			
			arrayFeature[rowFeature][9] += array200300400[r][9] ;//averageMidCenter_Black
			
			arrayFeature[rowFeature][10] += array200300400[r][10] ;//averageMidRight_White
			arrayFeature[rowFeature][11] += array200300400[r][11] ;//averageMidRight_Black
			
			arrayFeature[rowFeature][12] += array200300400[r][12] ;//averageDownLeft_White
			arrayFeature[rowFeature][13] += array200300400[r][13] ;//averageDownLeft_Black
			
			arrayFeature[rowFeature][14] += array200300400[r][14] ;//averageDownCenter_White
			arrayFeature[rowFeature][15] += array200300400[r][15] ;//averageDownCenter_Black
			
			arrayFeature[rowFeature][16] += array200300400[r][16] ;//averageDownRight_White
			arrayFeature[rowFeature][17] += array200300400[r][17] ;//averageDownRight_Black	
			
			
		}
		
		
		//เสร็จแล้วก็หารด้วย 3
		arrayFeature[rowFeature][0] = arrayFeature[rowFeature][0] / row234 ;//averageTopLeft_White
		arrayFeature[rowFeature][1] = arrayFeature[rowFeature][1] / row234 ;//averageTopLeft_Black
		
		arrayFeature[rowFeature][2] = arrayFeature[rowFeature][2] / row234 ;//averageTopCenter_White
		arrayFeature[rowFeature][3] = arrayFeature[rowFeature][3] / row234 ;//averageTopCenter_Black
		
		arrayFeature[rowFeature][4] = arrayFeature[rowFeature][4] / row234 ;//averageTopRight_White
		arrayFeature[rowFeature][5] = arrayFeature[rowFeature][5] / row234 ;//averageTopRight_Black
		
		arrayFeature[rowFeature][6] = arrayFeature[rowFeature][6] / row234 ;//averageMidLeft_White
		arrayFeature[rowFeature][7] = arrayFeature[rowFeature][7] / row234 ;//averageMidLeft_Black
		
		arrayFeature[rowFeature][8] = arrayFeature[rowFeature][8] / row234 ;//averageMidCenter_White
		arrayFeature[rowFeature][9] = arrayFeature[rowFeature][9] / row234 ;//averageMidCenter_Black
		
		arrayFeature[rowFeature][10] = arrayFeature[rowFeature][10] / row234 ;//averageMidRight_White
		arrayFeature[rowFeature][11] = arrayFeature[rowFeature][11] / row234 ;//averageMidRight_Black
		
		arrayFeature[rowFeature][12] = arrayFeature[rowFeature][12] / row234 ;//averageDownLeft_White
		arrayFeature[rowFeature][13] = arrayFeature[rowFeature][13] / row234 ;//averageDownLeft_Black
		
		arrayFeature[rowFeature][14] = arrayFeature[rowFeature][14] / row234 ;//averageDownCenter_White
		arrayFeature[rowFeature][15] = arrayFeature[rowFeature][15] / row234 ;//averageDownCenter_Black
		
		arrayFeature[rowFeature][16] = arrayFeature[rowFeature][16] / row234 ;//averageDownRight_White
		arrayFeature[rowFeature][17] = arrayFeature[rowFeature][17] / row234 ;//averageDownRight_Black
		
		//เสร็จแล้วก็ใส่คำตอบสุดท้ายลงใน txt
		WriteTXT.lineByLine( "หมายเลข "+Integer.toString( folderThai )+" { " );
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][0] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][1] ));			WriteTXT.lineByLine(" , ");
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][2] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][3] ));			WriteTXT.lineByLine(" , ");
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][4] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][5] ));			WriteTXT.lineByLine(" , \n                        ");
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][6] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][7] ));			WriteTXT.lineByLine(" , ");
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][8] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][9] ));			WriteTXT.lineByLine(" , ");
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][10] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][11] ));			WriteTXT.lineByLine(" , \n                        ");
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][12] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][13] ));			WriteTXT.lineByLine(" , ");
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][14] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][15] ));			WriteTXT.lineByLine(" , ");
		
//		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][16] ));			WriteTXT.lineByLine(" , ");
		WriteTXT.lineByLine(Double.toString( arrayFeature[rowFeature][17] ));			
		
		WriteTXT.lineByLine("     }");
		
		
	}//end  method  scanOneCharFeature
	
}