import java.nio.file.Files
import java.nio.file.Paths

def call(String sourcePath, String destinationPath)
{
  Files.copy( sourcePath, destinationPath)
}
