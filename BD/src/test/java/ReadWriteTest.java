import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import model.ReadWrite;


public class ReadWriteTest{

	@Test
	//Testa se o leitor de arquivo consegue trazer a �ltima linha do arquivo dados_buffer.
	public void readFileTest() throws IOException {
		List<String> list = ReadWrite.readFile("dados_buffer");
		assertEquals("&#60;fim T1>", list.get(2));
	}

}