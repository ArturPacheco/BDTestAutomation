import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import model.ReadWrite;


public class ReadWriteTest{

	@Test
	//Testa se o leitor de arquivo consegue trazer a última linha do arquivo dados_buffer.
	public void readFileTest() throws IOException {
		List<String> list = ReadWrite.readFile("dados_buffer");
		assertEquals("&#60;T1,artur,10,3>", list.get(2));
	}

}