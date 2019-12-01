package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadWrite;


@WebServlet("/Controle")
public class Controle extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*PrintWriter out = response.getWriter();
		out.println(request.getParameter("transacao"));*/
		
		String formulario = request.getParameter("nome_formulario");
		
		switch (formulario){
		case "novo_comando":
			//coleta dados dos formulário
			String transacao = request.getParameter("transacao");
			String nome = request.getParameter("nome");
			String nota = request.getParameter("nota");
			
			novoComando(transacao, nome, nota);
			break;
		case "checkpoint":
			checkpoint();
			break;
		case "reset":
			reset();
			break;
		case "commit":
			String transacao_commit = request.getParameter("transacao_commit");
			commit(transacao_commit);
			break;
		case "erro":
			erro();
			break;
		}
		
		
		//redireciona para index
		response.sendRedirect("http://localhost:8081/BD/index.jsp");
	}
	
	private static void erro() throws IOException{
		
		System.out.println("INICIANDO MÉTODO DE ERRO");
		
		List<String> log_disco = ReadWrite.readFile("log_disco");
		
		String t1 = null;
		String t2 = null;
		String t3 = null;
		String t4 = null;
		String t5 = null;
		
		boolean tem_checkpoint = false;
		
		//Collections.reverse(log_disco);
		
		System.out.println("identificando <fim TI> de cada transação");
		for(String item : log_disco){
    		item = item.replace("&#60;", "<");
    		if(item.startsWith("<fim T")){
    			switch (item.substring(6, 7)){
    			case "1":
    				t1 = "redo";
    				break;
    			case "2":
    				t2 = "redo";
    				break;
    			case "3":
    				t3 = "redo";
    				break;
    			case "4":
    				t4 = "redo";
    				break;
    			case "5":
    				t5 = "redo";
    				break;
    			}
    		}
    	}
		
		System.out.println("identificando <início TI> de cada transação");
		for(String item : log_disco){
			item = item.replace("&#60;", "<");
    		if(item.startsWith("<início T")){
    			switch (item.substring(9, 10)){
    			case "1":
    				if(t1 == null){
    					t1 = "undo";
    				}
    				break;
    			case "2":
    				if(t2 == null){
    					t2 = "undo";
    				}
    				break;
    			case "3":
    				if(t3 == null){
    					t3 = "undo";
    				}
    				break;
    			case "4":
    				if(t4 == null){
    					t4 = "undo";
    				}
    				break;
    			case "5":
    				if(t5 == null){
    					t5 = "undo";
    				}
    				break;
    			}
    		}
    	}
		
		System.out.println("verificando <checkpoint>");
		for(String item : log_disco){
			item = item.replace("&#60;", "<");
    		if(item.equals("<checkpoint>")){
    			System.out.println("<checkpoint> encontrado");
    			tem_checkpoint = true;
    		}
    	}
		
		if(tem_checkpoint == true){
			System.out.println("iniciando tratamento para lista com checkpoint");
			for(String item : log_disco){
				item = item.replace("&#60;", "<");
				if(item.equals("<checkpoint>")){
					System.out.println("tratamento de checkpoint encerrado");
	    			break;
	    		}
				if(item.startsWith("<fim T")){
					
	    			switch (item.substring(6, 7)){
	    			case "1":
	    				if(t1.equals("redo")){
	    					t1 = null;
	    				}
	    				break;
	    			case "2":
	    				if(t2.equals("redo")){
	    					t2 = null;
	    				}
	    				break;
	    			case "3":
	    				if(t3.equals("redo")){
	    					t3 = null;
	    				}
	    				break;
	    			case "4":
	    				if(t4.equals("redo")){
	    					t4 = null;
	    				}
	    				break;
	    			case "5":
	    				if(t5.equals("redo")){
	    					t5 = null;
	    				}
	    				break;
	    			}
	    		}
	    	}			
		}
		
		
		
		//undo(t1,t2,t3,t4,t5);
		
		//undo:não tem fim
    	//redo:tem fim
    	//o que tem fim antes do checkpoint sai da lista de redo
    	
		String nota_artur = null;
		String nota_giuliano = null;
		String nota_renan = null;
		String [] item_split = new String [3];
		
		
		
		//undo
		System.out.println("iniciando UNDO");
		Collections.reverse(log_disco);
		
		for(String item : log_disco){
			item = item.replace("&#60;", "<");
			switch (item.substring(2, 3)){
			case "1":
				if(t1 != null){
					if(t1.equals("undo")){
						item_split = item.split(",");
		    			switch (item_split[1]){
		    			case "artur":
		    				nota_artur = item_split[2];
		    				break;
		    			case "giuliano":
		    				nota_giuliano = item_split[2];
		    				break;
		    			case "renan":
		    				nota_renan = item_split[2];
		    				break;
		    			}
					}		
				}
				break;
				
			case "2":
				if(t2 != null){
					if(t2.equals("undo")){
						item_split = item.split(",");
		    			switch (item_split[1]){
		    			case "artur":
		    				nota_artur = item_split[2];
		    				break;
		    			case "giuliano":
		    				nota_giuliano = item_split[2];
		    				break;
		    			case "renan":
		    				nota_renan = item_split[2];
		    				break;
		    			}
					}		
				}
				break;
				
			case "3":
				if(t3 != null){
					if(t3.equals("undo")){
						item_split = item.split(",");
		    			switch (item_split[1]){
		    			case "artur":
		    				nota_artur = item_split[2];
		    				break;
		    			case "giuliano":
		    				nota_giuliano = item_split[2];
		    				break;
		    			case "renan":
		    				nota_renan = item_split[2];
		    				break;
		    			}
					}		
				}
				break;
				
			case "4":
				if(t4 != null){
					if(t4.equals("undo")){
						item_split = item.split(",");
		    			switch (item_split[1]){
		    			case "artur":
		    				nota_artur = item_split[2];
		    				break;
		    			case "giuliano":
		    				nota_giuliano = item_split[2];
		    				break;
		    			case "renan":
		    				nota_renan = item_split[2];
		    				break;
		    			}
					}		
				}
				break;
				
			case "5":
				if(t5 != null){
					if(t5.equals("undo")){
						item_split = item.split(",");
		    			switch (item_split[1]){
		    			case "artur":
		    				nota_artur = item_split[2];
		    				break;
		    			case "giuliano":
		    				nota_giuliano = item_split[2];
		    				break;
		    			case "renan":
		    				nota_renan = item_split[2];
		    				break;
		    			}
					}		
				}
				break;
			}
    	}
		
		//REDO
		System.out.println("iniciando REDO");
		Collections.reverse(log_disco);
		
		for(String item : log_disco){
			item = item.replace("&#60;", "<");
			switch (item.substring(2, 3)){
			case "1":
				if(t1 == null || t1.equals("redo")){
					item_split = item.split(",");
	    			switch (item_split[1]){
	    			case "artur":
	    				nota_artur = item_split[3];
	    				nota_artur = nota_artur.replace(">", "");
	    				break;
	    			case "giuliano":
	    				nota_giuliano = item_split[3];
	    				nota_giuliano = nota_giuliano.replace(">", "");
	    				break;
	    			case "renan":
	    				nota_renan = item_split[3];
	    				nota_renan = nota_renan.replace(">", "");
	    				break;
	    			}
				}
				break;
				
			case "2":
				if(t2 == null || t2.equals("redo")){
					item_split = item.split(",");
	    			switch (item_split[1]){
	    			case "artur":
	    				nota_artur = item_split[3];
	    				nota_artur = nota_artur.replace(">", "");
	    				break;
	    			case "giuliano":
	    				nota_giuliano = item_split[3];
	    				nota_giuliano = nota_giuliano.replace(">", "");
	    				break;
	    			case "renan":
	    				nota_renan = item_split[3];
	    				nota_renan = nota_renan.replace(">", "");
	    				break;
	    			}
				}
				break;
				
			case "3":
				if(t3 == null || t3.equals("redo")){
					item_split = item.split(",");
	    			switch (item_split[1]){
	    			case "artur":
	    				nota_artur = item_split[3];
	    				nota_artur = nota_artur.replace(">", "");
	    				break;
	    			case "giuliano":
	    				nota_giuliano = item_split[3];
	    				nota_giuliano = nota_giuliano.replace(">", "");
	    				break;
	    			case "renan":
	    				nota_renan = item_split[3];
	    				nota_renan = nota_renan.replace(">", "");
	    				break;
	    			}
				}
				break;
				
			case "4":
				if(t4 == null || t4.equals("redo")){
					item_split = item.split(",");
	    			switch (item_split[1]){
	    			case "artur":
	    				nota_artur = item_split[3];
	    				nota_artur = nota_artur.replace(">", "");
	    				break;
	    			case "giuliano":
	    				nota_giuliano = item_split[3];
	    				nota_giuliano = nota_giuliano.replace(">", "");
	    				break;
	    			case "renan":
	    				nota_renan = item_split[3];
	    				nota_renan = nota_renan.replace(">", "");
	    				break;
	    			}
				}
				break;
				
			case "5":
				if(t5 == null || t5.equals("redo")){
					item_split = item.split(",");
	    			switch (item_split[1]){
	    			case "artur":
	    				nota_artur = item_split[3];
	    				nota_artur = nota_artur.replace(">", "");
	    				break;
	    			case "giuliano":
	    				nota_giuliano = item_split[3];
	    				nota_giuliano = nota_giuliano.replace(">", "");
	    				break;
	    			case "renan":
	    				nota_renan = item_split[3];
	    				nota_renan = nota_renan.replace(">", "");
	    				break;
	    			}
				}
				break;
			}
    	}
		
		System.out.println("----listas----");
		System.out.println("t1:" + t1);
		System.out.println("t2:" + t2);
		System.out.println("t3:" + t3);
		System.out.println("t4:" + t4);
		System.out.println("t5:" + t5);
		System.out.println("----resultado----");
		System.out.println("artur," + nota_artur);
		System.out.println("giuliano," + nota_giuliano);
		System.out.println("renan," + nota_renan);
	}

	
	private static void commit (String transacao) throws IOException{
		
		
		//escreve no log_buffer o "fim" da transacao
		String comando = ("<fim "+transacao+">");
		ReadWrite.writeFile(comando,"log_buffer");
		ReadWrite.writeFile(comando,"dados_buffer");
		
		//coleta posição do checkpoint (se houver)
		int posicao_checkpoint = -1;
		List<String> lista = new ArrayList<>();
        lista = ReadWrite.readFile("log_disco");
        
        if (lista != null && lista.size() > 0) {
        	for(String item : lista){
        		if(item.equals("&#60;checkpoint>")){
        			posicao_checkpoint = lista.indexOf(item);
        		}
        	}
        }
		
        //copia dados do log_buffer para o log_disco
		ReadWrite.deleteData("log_disco");
		ReadWrite.copyData("log_buffer", "log_disco");
		
		//adiciona "<checkpoint>" na posição correta do log_disco
		lista = ReadWrite.readFile("log_disco");
        if (lista != null && lista.size() > 0 && posicao_checkpoint != -1) {
        	lista.add(posicao_checkpoint, "<checkpoint>");
        }
		
        //escreve no log_disco a lista (que contem o checkpoint no lugar correto)
        ReadWrite.deleteData("log_disco");
        for(String item : lista){
    		ReadWrite.writeFile(item, "log_disco");
    	}
		
	}
	
	private static void novoComando (String transacao, String nome, String nota) throws IOException{
		
		boolean tem_inicio = false;
		String comando;
		String nota_antiga = null;
		String [] item_split = new String [3];
			
		List<String> lista = new ArrayList<>();   
        lista = ReadWrite.readFile("log_buffer");
        
        //verifica se transação tem início
        if (lista != null && lista.size() > 0) {
        	for(String item : lista){
        		item = item.replace("&#60;", "<");
        		
        		if(item.equals("<início "+transacao+">")){
        			tem_inicio = true;
        		}
        	}
        }
        
        //escreve início no txt
        if(tem_inicio == false){
        	comando = ("<início "+transacao+">");
    		ReadWrite.writeFile(comando,"log_buffer");
    		tem_inicio = true;
        }
        
        //encontra nota antiga
        for(String item : lista){
    		item = item.replace("&#60;", "<");
    		
    		if(item.contains(nome)){
    			item_split = item.split(",");
    			nota_antiga = item_split[3];
    			nota_antiga = nota_antiga.replace(">", "");
    		}
    	}
        
		//escreve comando no txt
		comando = ("<"+transacao+","+nome+","+nota_antiga+","+nota+">");
		System.out.println("comando gerado: "+comando);
		
		ReadWrite.writeFile(comando,"log_buffer");
		ReadWrite.deleteData("dados_buffer");
		ReadWrite.copyData("log_buffer", "dados_buffer");
	}
	
	private static void checkpoint() throws IOException{
				
		List<String> lista = new ArrayList<>();   
		
		//limpa dados do log_disco e dados_disco
		ReadWrite.deleteData("log_disco");
		ReadWrite.deleteData("dados_disco");
		
		//copia dados do log_buffer para o txt log_disco
		lista = ReadWrite.readFile("log_buffer");
        if (lista != null && lista.size() > 0) {
        	for(String item : lista){
        		item = item.replace("&#60;", "<");
        		ReadWrite.writeFile(item,"log_disco");
        	}
        }
                
		//copia dados do dados_buffer para o txt dados_disco
		lista = ReadWrite.readFile("dados_buffer");
		String [] item_split = new String [3];
		String dado_artur = "artur,NULL";
		String dado_giuliano = "giuliano,NULL";
		String dado_renan = "renan,NULL";
        if (lista != null && lista.size() > 0) {
        	for(String item : lista){
        		//item = item.replace("&#60;", "<");
        		
        		item_split = item.split(",");
        		System.out.println(item_split);
        		if(item_split.length == 4 && item_split[1].equals("artur")){
        			dado_artur = item_split[1]+","+item_split[3];
        			dado_artur = dado_artur.replace(">", "");
        		}
        		if(item_split.length == 4 && item_split[1].equals("giuliano")){
        			dado_giuliano = item_split[1]+","+item_split[3];
        			dado_giuliano = dado_giuliano.replace(">", "");
        		}
        		if(item_split.length == 4 && item_split[1].equals("renan")){
        			dado_renan = item_split[1]+","+item_split[3];
        			dado_renan = dado_renan.replace(">", "");
        		}
        	}
        	ReadWrite.writeFile(dado_artur,"dados_disco");
        	ReadWrite.writeFile(dado_giuliano,"dados_disco");
        	ReadWrite.writeFile(dado_renan,"dados_disco");
        }
        
        //escreve o <checkpoint> no log_disco
        ReadWrite.writeFile("<checkpoint>","log_disco");
        
	}

	private static void reset() throws IOException{
		ReadWrite.deleteData("log_buffer");
		ReadWrite.deleteData("log_disco");
		ReadWrite.deleteData("dados_buffer");
		ReadWrite.deleteData("dados_disco");
	}
}
