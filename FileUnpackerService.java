
// File Unpacker Service Class
import java.io.*;

public class FileUnpackerService {
    
    public String unpackFiles(String packFileName) {
        StringBuilder result = new StringBuilder();
        
        try {
            File packFile = new File(packFileName);
            
            if (!packFile.exists()) {
                return "Error: Pack file '" + packFileName + "' does not exist.";
            }
            
            result.append("=== File Unpacking Process ===\n\n");
            result.append("Unpacking from: ").append(packFileName).append("\n\n");
            
            try (FileInputStream fin = new FileInputStream(packFile)) {
                byte[] header = new byte[100];
                int unpackedCount = 0;
                
                while (fin.read(header, 0, 100) > 0) {
                    String headerStr = new String(header).trim();
                    String[] parts = headerStr.split("\\s+");
                    
                    if (parts.length < 2) {
                        continue;
                    }
                    
                    String fileName = parts[0];
                    int fileSize;
                    
                    try {
                        fileSize = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        result.append("Warning: Invalid file size for ").append(fileName).append("\n");
                        continue;
                    }
                    
                    // Read file data
                    byte[] fileData = new byte[fileSize];
                    int bytesRead = fin.read(fileData, 0, fileSize);
                    
                    if (bytesRead != fileSize) {
                        result.append("Warning: Expected ").append(fileSize)
                              .append(" bytes but read ").append(bytesRead)
                              .append(" bytes for file ").append(fileName).append("\n");
                    }
                    
                    // Create and write the file
                    File outputFile = new File(fileName);
                    try (FileOutputStream fout = new FileOutputStream(outputFile)) {
                        fout.write(fileData, 0, bytesRead);
                    }
                    
                    result.append("✓ Extracted: ").append(fileName)
                          .append(" (").append(bytesRead).append(" bytes)\n");
                    unpackedCount++;
                }
                
                result.append("\n=== Summary ===\n");
                result.append("Files unpacked successfully: ").append(unpackedCount).append("\n");
                
                if (unpackedCount == 0) {
                    result.append("No files were found in the pack file.\n");
                }
                
            }
            
        } catch (Exception e) {
            result.append("Error occurred: ").append(e.getMessage()).append("\n");
            e.printStackTrace();
        }
        
        return result.toString();
    }
}