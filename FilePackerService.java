import java.io.*;

public class FilePackerService {
    
    public String packFiles(String folderName, String packFileName) {
        StringBuilder result = new StringBuilder();
        
        try {
            File folder = new File(folderName);
            
            if (!folder.exists() || !folder.isDirectory()) {
                return "Error: Directory '" + folderName + "' does not exist or is not a directory.";
            }
            
            File packFile = new File(packFileName);
            packFile.createNewFile();
            
            try (FileOutputStream fout = new FileOutputStream(packFile)) {
                File[] allFiles = folder.listFiles();
                
                if (allFiles == null || allFiles.length == 0) {
                    return "Error: Directory is empty or cannot be read.";
                }
                
                result.append("=== File Packing Process ===\n\n");
                result.append("Files being packed:\n");
                
                byte[] buffer = new byte[1024];
                int packedCount = 0;
                
                for (File file : allFiles) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        long fileSize = file.length();
                        
                        result.append("- ").append(fileName).append(" (").append(fileSize).append(" bytes)\n");
                        
                        // Create header: filename + size, padded to 100 bytes
                        String header = fileName + " " + fileSize;
                        while (header.length() < 100) {
                            header += " ";
                        }
                        
                        byte[] headerBytes = header.getBytes();
                        fout.write(headerBytes, 0, headerBytes.length);
                        
                        // Write file content
                        try (FileInputStream fin = new FileInputStream(file)) {
                            int bytesRead;
                            while ((bytesRead = fin.read(buffer)) != -1) {
                                fout.write(buffer, 0, bytesRead);
                            }
                        }
                        
                        packedCount++;
                    }
                }
                
                result.append("\n=== Summary ===\n");
                result.append("Total files scanned: ").append(allFiles.length).append("\n");
                result.append("Files packed successfully: ").append(packedCount).append("\n");
                result.append("Pack file created: ").append(packFileName).append("\n");
                
            }
            
        } catch (Exception e) {
            result.append("Error occurred: ").append(e.getMessage()).append("\n");
            e.printStackTrace();
        }
        
        return result.toString();
    }
}