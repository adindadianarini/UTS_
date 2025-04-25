# PENJELASAN KODE PROGRAM 

import java.util.ArrayList;                                             // Kelas array untuk menyimpan daftar pasien dalam bentuk list
import java.util.Scanner;                                               // kelas Scanner untuk membaca dari input pengguna

public class HospitalQueueSystemQuestion {                             // Mendeklarasikan kelas utama untuk sistem antrian rumah sakit
private static ArrayList<Patient> patientQueue = new ArrayList<>();    // Deklarasi list untuk menyimpan antrian pasien
private static Scanner scanner = new Scanner(System.in);               // Membuat objek Scanner untuk input pengguna

public static void main(String[] args) {                              // Metode utama untuk menjalankan program
boolean running = true;                                               // Variabel untuk menentukan apakah sistem masih berjalan

System.out.println("Welcome to Hospital Queue Management System");    // Menampilkan pesan selamat datang, dengan teks "Welcome to Hospital Queue Management System"

while (running) {                                                     // Loop utama untuk menjalankan menu sistem
displayMenu(); // Memanggil metode untuk menampilkan menu
int choice = getValidIntInput("Enter your choice: ");                // Membaca pilihan pengguna dengan validasi

switch (choice) {                 // Memproses pilihan yang nanti di inputkan oleh pengguna dengan struktur switch-case
case 1:                          // Jika yang di inputkan angka 1, maka akan menambahkan pasien ke antrian
addPatient(); 
break;
case 2:                          // Jika yang di inputkan angka 2, melayani pasien berikutnya
serveNextPatient();
break;
case 3:                         // Jika yang di inputkan angka 3, akan ditampilkan antrian pasien saat ini
displayQueue();
break;
case 4:                        // Jika yang di inputkan angka 4, memperbarui prioritas pasien 
updatePriority();
break;
case 5:                       // Jika  menginputkan angka 5, berarti mencari pasien berdasarkan nama 
searchPatient();
break;
case 6:                      // Jika angka yang di input adalah 6, maka akan keluar dari sistem dan akan menampilkan pesan melalui System.out.println seperti kode dibawah
System.out.println("Thank you for using Hospital Queue Management System. Goodbye!");

running = false;                                                     // Kode ini Mengubah variabel running menjadi false untuk keluar dari loop
break;
default:                                                            // Jika ada inputan pilihan yang tidak valid maka akan menampilkan teks seperti ini
System.out.println("Invalid choice. Please try again.");
  }
}
scanner.close();                                                   // Menutup pembaca input (Scanner) setelah selesai digunakan
  }

private static void displayMenu() {                               // Metode untuk menampilkan menu sistem 
System.out.println("\n===== HOSPITAL QUEUE SYSTEM =====");        // Mencetak header menu
System.out.println("1. Add a new patient to the queue");          // mencetak keterangan Pilihan 1 untuk menambah pasien
System.out.println("2. Serve next patient");                      // mencetak keterangan Pilihan 2 Pilihan untuk melayani pasien berikutnya
System.out.println("3. Display current queue");                   // mencetak keterangan Pilihan 3 Pilihan untuk menampilkan antrian pasien
System.out.println("4. Update patient priority");                 // mencetak keterangan Pilihan 4 Pilihan untuk memperbarui prioritas pasien
System.out.println("5. Search for a patient");                    // mencetak keterangan Pilihan 5 Pilihan untuk mencari pasien
System.out.println("6. Exit");                                    // mencetak keterangan Pilihan 6 Pilihan untuk keluar dari sistem
System.out.println("=================================");          // Penutup menu
    }

private static void addPatient() {                                                         // Metode untuk menambahkan pasien ke antrian
System.out.println("\n--- Add New Patient ---");                                           // Menampilkan header untuk bagian ini
String name = getValidStringInput("Enter patient's name: ");                               // Input nama pasien
int age = getValidIntInput("Enter patient's age: ");                                       // Input usia pasien
String condition = getValidStringInput("Enter condition description: ");                   // Input kondisi pasien
int priority = getValidIntInRange("Enter priority (1-Critical to 5-Non-urgent): ", 1, 5);  // Input prioritas pasien

Patient newPatient = new Patient(name, age, condition, priority);                                  // Membuat objek pasien baru

        // Menyisipkan pasien berdasarkan prioritas (angka lebih kecil = prioritas lebih tinggi)
int index = 0;                                                                                     // Variabel untuk menentukan posisi penyisipan
while (index < patientQueue.size() && patientQueue.get(index).getPriority() <= priority) {
index++;                                                                                           // Iterasi hingga menemukan posisi yang sesuai untuk menyisipkan pasien
}
patientQueue.add(index, newPatient);                                                               // Menambahkan pasien pada posisi yang sesuai
System.out.println("Patient added successfully."); // Pesan konfirmasi
}

private static void serveNextPatient() {                                                 // Metode untuk melayani pasien berikutnya
if (patientQueue.isEmpty()) {                                                            // Memeriksa apakah antrian kosong
System.out.println("No patients in queue.");                                             // Menampilkan pesan jika antrian kosong
} else { // Jika ada pasien dalam antrian
Patient next = patientQueue.remove(0);                                                  // Menghapus pasien pertama dari antrian
System.out.println("Serving patient: " + next.getName() +
                " | Age: " + next.getAge() +
                " | Condition: " + next.getCondition() +
                " | Priority: " + getPriorityText(next.getPriority()));                 // Menampilkan detail pasien yang sedang dilayani
        }
    }

private static void displayQueue() {                                                   // Metode untuk menampilkan antrian pasien
if (patientQueue.isEmpty()) {                                                          // Memeriksa apakah antrian kosong
System.out.println("No patients in queue.");                                           // Menampilkan pesan jika antrian kosong
} else {                                                                               // Jika ada pasien dalam antrian
System.out.println("\n--- Current Patient Queue ---");                                 // Header untuk bagian ini
for (int i = 0; i < patientQueue.size(); i++) {                                        // Iterasi melalui daftar pasien
Patient p = patientQueue.get(i);                                                       // Mendapatkan pasien pada posisi tertentu
System.out.println((i + 1) + ". " + p.getName() +
                    " | Age: " + p.getAge() +
                    " | Condition: " + p.getCondition() +
                    " | Priority: " + getPriorityText(p.getPriority()));               // Menampilkan detail pasien
            }
        }
    }
