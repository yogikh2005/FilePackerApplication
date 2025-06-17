# FilePackerApplication
File packer and unpacker application 



A cross-platform desktop application that allows you to **pack multiple files** from a directory into a single encrypted file and **unpack** them back. Built using **Java AWT + Swing** for GUI and **core Java I/O & multithreading** for backend logic.

---

## 📋 Features

- 🔐 **Secure login** with 3 attempts and Caps Lock detection
- 🧠 Multithreading for input validation (length check, Caps Lock detection)
- 📦 **Packing** files from a directory into one encrypted file with metadata
- 📂 **Unpacking** a packed file and restoring original files
- 📄 **Metadata header** contains filename, size, and checksum (for validation)
- 🧾 **Packing log** maintained in system directory for traceability
- 🚫 Automatic program exit after 3 failed login attempts
- ✳️ Magic number validation for verifying packed file integrity

---

## 🧑‍💻 Technologies Used

- Java 11+
- AWT & Swing (UI)
- Java I/O (FileInputStream, FileOutputStream, BufferedReader, etc.)
- Java Threads

- Cross-platform (Windows/Linux/macOS)

---


## 🔐 Authentication Workflow

* Default username: `abc`
* Default password: `123`

---

## ⚙️ Packing Logic (Backend)

* In case of Packing activity we accept directory name and file name from user. 
* We have to create new regular file as the name specified by the user. 
* Now open the directory and traverse each file from that directory. In newly created file 
write Metadata as header and actual file data in sequence. 
* While writing data perform encryption.  
* Each name of file ,its size and checksum should be written in log file which gets created in system directory. 
* After packing display packing report.
---

## 🧩 Unpacking Logic (Backend)

1.In case of UnPacking activity we accept packed file name from user. 
2.for authentication of packed file use any logic like Magic Number. 
3.Open the packed file in read mode and perform below activity as 
4.Read header 
5.From the name specified in header create new file. 
6.Write data into newly created file from packed file. 
7.After unpacking display unpacking report. 

---

## 🚀 How to Run

1. Clone the repository:

```bash
git clone https://github.com/yogikh2005/FilePackerUnpacker.git
cd FilePackerUnpacker
```

2. Compile the Java source code:

```bash
javac src/*.java
```

3. Run the application:

```bash
java src/
```

---

## 🧪 Future Enhancements

* Add AES-based strong encryption
* File compression before packing
* Support for folder hierarchies
* Drag and drop UI features
* Upload to cloud after packing

