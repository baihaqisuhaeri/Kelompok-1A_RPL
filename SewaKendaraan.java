import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



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
	 			System.out.print("Masukan alamat anda : ");
	 			alamat = masukan.nextLine();
	 			System.out.print("Masukan nomor handphone anda : ");
	 			noHp = masukan.nextLine();
	 			System.out.print("Masukan durasi (Hari) sewa yang anda inginkan : ");
	 			durasiSewa = masukan.nextInt();
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
	 						tambahTransaksi = connection.prepareStatement("INSERT INTO transaksi (kodeTf, nama, noKtp, namaKendaraan, durasiSewa, hargaSewa)" +" VALUES (?, ?, ?, ?, ?, ?) ");
							tambahTransaksi.setString(1,kodeTf);
							tambahTransaksi.setString(2,nama);
							tambahTransaksi.setString(3, noKtp);
							tambahTransaksi.setString(4, "Mobil " + mobilDipilih);
							tambahTransaksi.setInt(5, durasiSewa);
							tambahTransaksi.setInt(6, akumulasiHarga);
							int result = tambahTransaksi.executeUpdate();

	 				}catch(SQLException e){
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
	 			System.out.print("Masukan alamat anda : ");
	 			alamat = masukan.nextLine();
	 			System.out.print("Masukan nomor handphone anda : ");
	 			noHp = masukan.nextLine();
	 			System.out.print("Masukan durasi (Hari) sewa yang anda inginkan : ");
	 			durasiSewa = masukan.nextInt();
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
	 						tambahTransaksi = connection.prepareStatement("INSERT INTO transaksi (kodeTf, nama, noKtp, namaKendaraan, durasiSewa, hargaSewa)" +" VALUES (?, ?, ?, ?, ?, ?) ");
							tambahTransaksi.setString(1,kodeTf);
							tambahTransaksi.setString(2,nama);
							tambahTransaksi.setString(3, noKtp);
							tambahTransaksi.setString(4, "Motor " + motorDipilih);
							tambahTransaksi.setInt(5, durasiSewa);
							tambahTransaksi.setInt(6, akumulasiHarga);
							int result = tambahTransaksi.executeUpdate();

	 				}catch(SQLException e){
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

	 }
	
}


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