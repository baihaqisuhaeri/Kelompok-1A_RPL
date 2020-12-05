import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.time.*;
import java.time.format.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SewaKendaraan{

    private static final String URL = "jdbc:mysql://localhost:3306/sewakendaraan";
       private static final String USERNAME = "root";
       private static final String PASSWORD = "";
       private static Connection connection;
       private static PreparedStatement tambahPenyewa;
       private static PreparedStatement tambahTransaksi;



	public static void main(String[] args){
		 Scanner masukan = new Scanner(System.in);
		
		Mobil avanza = new Mobil("Avanza", "Putih", 6, 500000);
		Mobil xenia = new Mobil("Xenia", "Biru", 6, 450000);
		Mobil brio = new Mobil("Brio" , "Merah", 4, 400000);
		Mobil mobilio = new Mobil("Mobilio", "Hitam", 5, 470000);
		Mobil[] mobil = {avanza, xenia, brio, mobilio};

		Motor mio = new Motor("Mio", "Merah", "Matic", 150000);
		Motor beat = new Motor("Beat", "Biru", "Matic", 170000);
		Motor cbr = new Motor("CBR", "Putih", "Kopling", 300000);
		Motor byson = new Motor("Byson FI", "Hitam", "Kopling", 350000);
		Motor[] motor = {mio, beat, cbr, byson};
try{

	connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
}catch (SQLException e){ 
	throw new RuntimeException(e);
 }


 	
 		boolean cekHome = true;
 		while(cekHome){
 	 String nama;
 	 String noKtp;
 	 String alamat;
 	 String noHp;
 	 String kodeTf;
	 String inputTf;
	 String tanggal;
	 int denda = 5000;
	 int totalDenda;
	 int hasilDurasi;
	 int bayarDenda;

	 String stglAwal;
     String stglAkhir;

	 System.out.println("Selamat Datang Di Apilikasi Sewa Kendaraan Kelompok 1");
	 System.out.println("-------------------------------------");
	 System.out.println("Masukan Pilihan Anda");
	 System.out.println("1. Sewa Kendaraan");
	 System.out.println("2. Pengembalian Kendaraan");
	 System.out.print("Pilih : ");
	 int kendaraan;
	 int uang;
	 String mobilDipilih;
	 String motorDipilih;
	 int akumulasiHarga;
	 int yakinSewa2;
	 int durasiSewa;
	 int pilihMobil;
	 int pilihMotor;
	 int pilihanSewa;
	 int yakinSewa;
	 int pilihan1 = masukan.nextInt();
	 if(pilihan1==1){
	 	System.out.println("Pilih kendaraan yang ingin anda sewa");
	 	System.out.println("1. Mobil");
	 	System.out.println("2. Motor");
	 	System.out.print("Pilih : ");
	 	pilihanSewa = masukan.nextInt();
	 	if(pilihanSewa==1){
	 		System.out.println("Pilih jenis Mobil yang ingin anda sewa");
	 		for(int i=0; i<mobil.length; i++){
	 			System.out.println(i+1 + ". " + mobil[i].getMerekMobil());
	 			System.out.println("   Warna : " + mobil[i].getWarna());
	 			System.out.println("   Tempat Duduk : " + mobil[i].getTempatDuduk());
	 			System.out.println("   Harga : " + mobil[i].getHarga());
	 			System.out.println();
	 		}
	 		System.out.print("Pilih : ");
	 		pilihMobil = masukan.nextInt();
	 		System.out.println("Sewa mobil yang dipilih?");
	 		System.out.println("1. Ya");
	 		System.out.println("2. Tidak");
	 		System.out.println("3. Keluar");
	 		System.out.print("Pilih : ");
	 		yakinSewa = masukan.nextInt();
	 		if(yakinSewa==1){
	 			System.out.println("Masukan data diri anda");
	 			System.out.print("Nama : ");
	 			nama = masukan.nextLine();
	 			nama = masukan.nextLine();
	 			System.out.print("Masukan nomor KTP : ");
	 			noKtp = masukan.nextLine();
	 			if(!noKtp.matches("(^\\d+${16})")){
	 				System.out.println("Nomor KTP harus angka 16 digit");
	 				System.out.print("Masukan nomor KTP : ");
	 				noKtp = masukan.nextLine();
	 			}
	 			System.out.print("Masukan alamat anda : ");
	 			alamat = masukan.nextLine();
	 			System.out.print("Masukan nomor handphone anda : ");
	 			noHp = masukan.nextLine();
	 			if(!noHp.matches("(^\\d+${10,13})")){
	 				System.out.println("Nomor handphone harus angka 10-13 digit");
	 				System.out.print("Masukan nomor handphone anda : ");
	 				noHp = masukan.nextLine();
	 			}
	 			System.out.print("Masukan durasi (Hari) sewa yang anda inginkan : ");
	 			durasiSewa = masukan.nextInt();
	 			if(durasiSewa < 1 || !("" + durasiSewa).matches("(^\\d+$)")){
	 				System.out.print("Masukan durasi (Hari) sewa yang anda inginkan : ");
	 				durasiSewa = masukan.nextInt();
	 			}
	 			System.out.print("Masukan tanggal sewa : ");
	 			tanggal = masukan.next();
	 			boolean cekFormatTgl = validateDate(tanggal);
	 			if(cekFormatTgl == false){
	 				System.out.print("Masukan tanggal sewa : ");
	 				tanggal = masukan.next();
	 				cekFormatTgl = validateDate(tanggal);
	 			}
	 			System.out.println();
	 			akumulasiHarga = durasiSewa * mobil[pilihMobil-1].getHarga();
	 			mobilDipilih = mobil[pilihMobil-1].getMerekMobil();
	 			System.out.println("Harga sewa mobil " + mobil[pilihMobil-1].getMerekMobil() +" untuk " + durasiSewa + " hari adalah Rp." + akumulasiHarga);
	 			System.out.println("Yakin ingin menyewa mobil yang sudah anda pilih?");
	 			System.out.println("1. Ya");
	 			System.out.println("2. Tidak");
	 			System.out.println("3. Keluar");
	 			System.out.print("Pilih : ");
	 			yakinSewa2 = masukan.nextInt();
	 			if(yakinSewa2==1){
	 				System.out.print("Masukan uang anda : ");
	 				uang = masukan.nextInt();
	 				kodeTf = noKtp.substring(0,noKtp.length()/2) + noHp.substring(noHp.length()/2,noHp.length());

	 				try{
	 						tambahPenyewa = connection.prepareStatement("INSERT INTO penyewa (nama, noKtp, alamat, noHp)" +" VALUES (?, ?, ?,?) ");
							tambahPenyewa.setString(1,nama);
							tambahPenyewa.setString(2,noKtp);
							tambahPenyewa.setString(3, alamat);
							tambahPenyewa.setString(4, noHp);
							int result = tambahPenyewa.executeUpdate();

	 				}catch(SQLException e){
	 					e.printStackTrace();
	 				}


	 					try{
	 						tambahTransaksi = connection.prepareStatement("INSERT INTO transaksi (kodeTf, nama, noKtp, namaKendaraan, durasiSewa, hargaSewa, tanggalSewa)" +" VALUES (?, ?, ?, ?, ?, ?, ?) ");
							tambahTransaksi.setString(1,kodeTf);
							tambahTransaksi.setString(2,nama);
							tambahTransaksi.setString(3, noKtp);
							tambahTransaksi.setString(4, "Mobil " + mobilDipilih);
							tambahTransaksi.setInt(5, durasiSewa);
							tambahTransaksi.setInt(6, akumulasiHarga);

							DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
							Date fd = formatter.parse(tanggal);
							java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
							tambahTransaksi.setDate(7, sqlDate);

							int result = tambahTransaksi.executeUpdate();

	 				}catch(Exception e){
	 					e.printStackTrace();
	 				}


	 				if(uang>=akumulasiHarga){
	 					System.out.println("Transaksi anda berhasil");
	 					System.out.println("Detail transaksi : ");
	 					System.out.println("Kode Transaksi : " + kodeTf);
	 					System.out.println("Nama Penyewa : " + nama);
	 					System.out.println("Nomor KTP Penyewa : " + noKtp);
	 					System.out.println("Alamat Penyewa : " + alamat);
	 					System.out.println("Nomor Handphone Penywa : " + noHp);
	 					System.out.println("Durasi Sewa : " + durasiSewa + " Hari");
	 					System.out.println("Total harga : Rp." + akumulasiHarga);
	 					System.out.println("Uang yang dibayarkan : Rp."+ uang);
	 					System.out.println("Kembalian uang anda : Rp." + (uang - akumulasiHarga));
	 					cekHome=false;
	 				}else{
	 					System.out.println("Maaf uang anda tidak cukup untuk membayar harga sewa, silahkan membayar lagi nanti dengan Kode Transaksi anda : " + kodeTf);
	 					cekHome=false;
	 				}


	 			} else if(yakinSewa2==2){
	 				System.out.println();
	 			System.out.println("Sewa dibatalkan");
	 			System.out.println();

	 		} else if(yakinSewa2==3){
	 			System.out.println("Terima kasih telah menggunakan apilikasi Sewa Kendaraan ini.");
	 			cekHome=false;

	 		}


	 		}
	 		else if(yakinSewa==2){
	 			System.out.println();
	 			System.out.println("Sewa dibatalkan");
	 			System.out.println();
	 		} else if(yakinSewa==3){
	 			System.out.println("Terima kasih telah menggunakan apilikasi Sewa Kendaraan ini.");
	 			cekHome=false;

	 		}

	 	}else if(pilihanSewa==2){
	 		System.out.println("Pilih jenis Motor yang ingin anda sewa");
	 		for(int i=0; i<motor.length; i++){
	 			System.out.println(i+1 + ". " + motor[i].getMerekMotor());
	 			System.out.println("   Warna : " + motor[i].getWarna());
	 			System.out.println("   Harga : " + motor[i].getHarga());
	 			System.out.println();
	 		}
	 		System.out.print("Pilih : ");
	 		pilihMotor = masukan.nextInt();
	 		System.out.println("Sewa motor yang dipilih?");
	 		System.out.println("1. Ya");
	 		System.out.println("2. Tidak");
	 		System.out.println("3. Keluar");
	 		System.out.print("Pilih : ");
	 		yakinSewa = masukan.nextInt();
	 		if(yakinSewa==1){
	 			System.out.println("Masukan data diri anda");
	 			System.out.print("Nama : ");
	 			nama = masukan.nextLine();
	 			nama = masukan.nextLine();
	 			System.out.print("Masukan nomor KTP : ");
	 			noKtp = masukan.nextLine();
	 			if(!noKtp.matches("(^\\d+${16})")){
	 				System.out.println("Nomor KTP harus angka 16 digit");
	 				System.out.print("Masukan nomor KTP : ");
	 				noKtp = masukan.nextLine();
	 			}
	 			System.out.print("Masukan alamat anda : ");
	 			alamat = masukan.nextLine();
	 			System.out.print("Masukan nomor handphone anda : ");
	 			noHp = masukan.nextLine();
	 			if(!noHp.matches("(^\\d+${10,13})")){
	 				System.out.println("Nomor handphone harus angka 10-13 digit");
	 				System.out.print("Masukan nomor handphone anda : ");
	 				noHp = masukan.nextLine();
	 			}
	 			System.out.print("Masukan durasi (Hari) sewa yang anda inginkan : ");
	 			durasiSewa = masukan.nextInt();
	 			if(durasiSewa < 1 || !("" + durasiSewa).matches("(^\\d+$)")){
	 				System.out.print("Masukan durasi (Hari) sewa yang anda inginkan : ");
	 				durasiSewa = masukan.nextInt();
	 			}
	 			System.out.print("Masukan tanggal sewa : ");
	 			tanggal = masukan.next();
	 			boolean cekFormatTgl = validateDate(tanggal);
	 			if(cekFormatTgl == false){
	 				System.out.print("Masukan tanggal sewa : ");
	 				tanggal = masukan.next();
	 				cekFormatTgl = validateDate(tanggal);
	 			}
	 			System.out.println();
	 			akumulasiHarga = durasiSewa * motor[pilihMotor-1].getHarga();
	 			motorDipilih = motor[pilihMotor-1].getMerekMotor();
	 			System.out.println("Harga sewa motor " + motor[pilihMotor-1].getMerekMotor() + " untuk " + durasiSewa + " hari adalah Rp." + akumulasiHarga);
	 			System.out.println("Yakin ingin menyewa motor yang sudah anda pilih?");
	 			System.out.println("1. Ya");
	 			System.out.println("2. Tidak");
	 			System.out.println("3. Keluar");
	 			System.out.print("Pilih : ");
	 			yakinSewa2 = masukan.nextInt();
	 			if(yakinSewa2==1){
	 				System.out.print("Masukan uang anda : ");
	 				uang = masukan.nextInt();
	 				kodeTf = noKtp.substring(0,noKtp.length()/2) + noHp.substring(noHp.length()/2,noHp.length());

	 				try{
	 						tambahPenyewa = connection.prepareStatement("INSERT INTO penyewa (nama, noKtp, alamat, noHp)" +" VALUES (?, ?, ?,?) ");
							tambahPenyewa.setString(1,nama);
							tambahPenyewa.setString(2,noKtp);
							tambahPenyewa.setString(3, alamat);
							tambahPenyewa.setString(4, noHp);
							int result = tambahPenyewa.executeUpdate();

	 				}catch(SQLException e){
	 					e.printStackTrace();
	 				}


	 					try{
	 						tambahTransaksi = connection.prepareStatement("INSERT INTO transaksi (kodeTf, nama, noKtp, namaKendaraan, durasiSewa, hargaSewa, tanggalSewa)" +" VALUES (?, ?, ?, ?, ?, ?, ?) ");
							tambahTransaksi.setString(1,kodeTf);
							tambahTransaksi.setString(2,nama);
							tambahTransaksi.setString(3, noKtp);
							tambahTransaksi.setString(4, "Motor " + motorDipilih);
							tambahTransaksi.setInt(5, durasiSewa);
							tambahTransaksi.setInt(6, akumulasiHarga);
							DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
							Date fd = formatter.parse(tanggal);
							java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
							tambahTransaksi.setDate(7, sqlDate);
							int result = tambahTransaksi.executeUpdate();

	 				}catch(Exception e){
	 					e.printStackTrace();
	 				}


	 				if(uang>=akumulasiHarga){
	 					System.out.println("Transaksi anda berhasil");
	 					System.out.println("Detail transaksi : ");
	 					System.out.println("Kode Transaksi : " + kodeTf);
	 					System.out.println("Nama Penyewa : " + nama);
	 					System.out.println("Nomor KTP Penyewa : " + noKtp);
	 					System.out.println("Alamat Penyewa : " + alamat);
	 					System.out.println("Nomor Handphone Penywa : " + noHp);
	 					System.out.println("Durasi Sewa : " + durasiSewa + " Hari");
	 					System.out.println("Total harga : Rp." + akumulasiHarga);
	 					System.out.println("Uang yang dibayarkan : Rp."+ uang);
	 					System.out.println("Kembalian uang anda : Rp." + (uang - akumulasiHarga));
	 					cekHome=false;
	 				}else{
	 					System.out.println("Maaf uang anda tidak cukup untuk membayar harga sewa, silahkan membayar lagi nanti dengan Kode Transaksi anda : " + kodeTf);
	 					cekHome=false;
	 				}


	 			} else if(yakinSewa2==2){
	 				System.out.println();
	 			System.out.println("Sewa dibatalkan");
	 			System.out.println();

	 		} else if(yakinSewa2==3){
	 			System.out.println("Terima kasih telah menggunakan apilikasi Sewa Kendaraan ini.");
	 			cekHome=false;

	 		}


	 		}
	 		else if(yakinSewa==2){
	 			System.out.println();
	 			System.out.println("Sewa dibatalkan");
	 			System.out.println();
	 		} else if(yakinSewa==3){
	 			System.out.println("Terima kasih telah menggunakan apilikasi Sewa Kendaraan ini.");
	 			cekHome=false;

	 		}

	 	}



	 }
	 //pengembalian

	 else if(pilihan1==2){
	 	System.out.print("Masukan kode transaksi anda : ");
	 	inputTf = masukan.next();
	 	System.out.print("Masukan tanggal pengembalian : ");
	 	stglAkhir = masukan.next();
	 	try{
	 	PreparedStatement stmt = connection.prepareStatement("select * from transaksi where kodeTf= '"+inputTf+"'");  
		ResultSet rs = stmt.executeQuery();
		DatabaseMetaData md = connection.getMetaData();
		ResultSet rs2 = md.getColumns(null, null, "transaksi", "kodeTf");
	if (rs2.next()) {
      //Column in table exist
		System.out.println("Maaf transaksi yang anda cari tidak ada atau mungkin anda belum menyewa kendaraan kami.");
    	}else{
    	}
		  while(rs.next()){
		  	System.out.println();
		  	System.out.println("=====Detail transaksi=====");
			System.out.println("Kode Transaksi : "+rs.getString(1));
			System.out.println("Nama Penyewa : "+rs.getString(2));
			System.out.println("Nomor KTP Penyewa : "+rs.getString(3));
			System.out.println("Nama Kendaraan : "+rs.getString(4));
			System.out.println("Durasi Sewa : "+rs.getInt(5));
			hasilDurasi = rs.getInt(5);
			System.out.println("Harga Sewa : " +rs.getInt(6));
			System.out.println("Tanggal Sewa : "+ rs.getDate(7));
			System.out.println("Tanggal Sewa : "+ stglAkhir);

			Date date = rs.getDate(7);  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
            stglAwal = dateFormat.format(date); 
            

            DateFormat dateAwal = new SimpleDateFormat("yyyy-mm-dd");
        DateFormat dateAkhir = new SimpleDateFormat("yyyy-mm-dd");

             Date tglAwal = dateAwal.parse(stglAwal);
            Date tglAkhir = dateAkhir.parse(stglAkhir);
            
            Date TGLAwal = tglAwal;
            Date TGLAkhir = tglAkhir;
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(TGLAwal);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(TGLAkhir);
            
            String hasil = String.valueOf(daysBetween(cal1, cal2));
            
            if(Integer.parseInt(hasil)>hasilDurasi){
            	System.out.println();
            	System.out.println("Maaf anda telah melewati batas peminjaman, mohon untuk membayar dendanya!");

            	System.out.println("Harga Denda/Hari : Rp." + denda);
            	System.out.println("Denda yang harus dibayarkan : Rp." + denda*(Integer.parseInt(hasil)-hasilDurasi));
            	totalDenda = denda*(Integer.parseInt(hasil)-hasilDurasi);
            	System.out.print("Masukan uang untuk membayar denda : ");
            	bayarDenda = masukan.nextInt();
            	while(bayarDenda<totalDenda){
            		System.out.println("Jangan kabur, uang yang dimasukan tidak cukup, bayar dendanya dulu!!!");
            		System.out.print("Masukan uang untuk membayar denda : ");
            		bayarDenda = masukan.nextInt();
            		if(bayarDenda>=totalDenda){
            			System.out.println("Denda sudah dibayar");
            		}
            	}

            }


			System.out.println("=====Terima Kasih Telah Mengembalikan Kendaraan=====");  
			
			}
			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
	 	}
	 }
	
}


	}


private static long daysBetween(Calendar tanggalAwal, Calendar tanggalAkhir) {
        long lama = 0;
        Calendar tanggal = (Calendar) tanggalAwal.clone();
        while (tanggal.before(tanggalAkhir)) {
            tanggal.add(Calendar.DAY_OF_MONTH, 1);
            lama++;
        }
        return lama;
    }


public static void addAuthor(String nama, String noKtp, String alamat, String noHp){
	try{
		tambahPenyewa.setString(1,nama);
		tambahPenyewa.setString(2,noKtp);
		tambahPenyewa.setString(3, alamat);
		tambahPenyewa.setString(4, noHp);
		int result = tambahPenyewa.executeUpdate();
	}catch (SQLException e){
	 e.printStackTrace();
	}
}


public static boolean validateDate(String strDate){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	sdf.setLenient(false);
	try {
		Date tgl = sdf.parse(strDate);
	    return true;
	} catch (ParseException e) {
	    System.out.println("Format tanggal harus yyyy-MM-dd");
	    return false;
	}
}


}

class Mobil{
	private String merekMobil;
	public void setMerekMobil(String merekMobil){
		this.merekMobil = merekMobil;
	}
	public String getMerekMobil(){
		return merekMobil;
	}

	private String warna;
	public void setWarna(String warna){
		this.warna=warna;
	}
	public String getWarna(){
		return warna;
	}

	private int tempatDuduk;
	public void setTempatDuduk(int tempatDuduk){
		this.tempatDuduk = tempatDuduk;
	}
	public int getTempatDuduk(){
		return tempatDuduk;
	}

	private int harga;
	public void setHarga(int harga){
		this.harga = harga;
	}
	public int getHarga(){
		return harga;
	}
	public Mobil(String merekMobil, String warna, int tempatDuduk, int harga){
		this.merekMobil=merekMobil;
		this.warna=warna;
		this.tempatDuduk=tempatDuduk;
		this.harga=harga;
	}
}


class Motor{
	private String merekMotor;
	public void setMerekMotor(String merekMotor){
		this.merekMotor = merekMotor;
	}
	public String getMerekMotor(){
		return merekMotor;
	}

	private String warna;
	public void setWarna(String warna){
		this.warna=warna;
	}
	public String getWarna(){
		return warna;
	}

	private String transmisi;
	public void setTransmisi(String transmisi){
		this.transmisi=transmisi;
	}
	public String getTransmisi(){
		return transmisi;
	}

	private int harga;
	public void setHarga(int harga){
		this.harga = harga;
	}
	public int getHarga(){
		return harga;
	}
	public Motor(String merekMotor, String warna, String transmisi, int harga){
		this.merekMotor=merekMotor;
		this.warna=warna;
		this.transmisi=transmisi;
		this.harga=harga;
	}
}