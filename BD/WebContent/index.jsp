<%@page import="model.ReadWrite"%>
<%@page import="control.Controle"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<title>Recup de Falhas</title>
</head>
<body>
	<div class="container" style="background-color:#b3b3b3;">
      <div class="row">
      	<div class="col-md-11 mt-md-2">
      		<span class="h4">Recuperação de falhas</span>
      	</div>
        <div class="col-md-1 mt-md-2">
     	  <form action="Controle" method="POST">
          	<input type="hidden" name="nome_formulario" value="reset">
          	<button type="submit" class="btn btn-basic w-100">reset</button>
          </form>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col">
          <h6>NOVO COMANDO</h6>
        </div>
        <div class="col">
          <h6>LOG (BUFFER)</h6>
        </div>
        <div class="col">
          <h6>DADOS (BUFFER)</h6>
        </div>
      </div>
      
      <div class="row" style="height: 200px;">
        <div class="col-sm-4">      
            
          <!-- ADICIONAR COMANDOS -->
          <form action="Controle" method="POST">
         	 <div class="form-row mb-md-2">
              <div class="col">
                <label class="sr-only" for="sel">T</label>
                <select name="transacao" class="form-control form-control-sm" id="sel">
                  <option value="T1">T1</option>
                  <option value="T2">T2</option>
                  <option value="T3">T3</option>
                  <option value="T4">T4</option>
                  <option value="T5">T5</option>
                </select>
              </div>
            </div>
            <h6>UPDATE alunos</h6>
            <div class="form-row mb-md-2">
              <div class="col">
                <label for="nota">SET nota =</label>
              </div>
              <div class="col">
                <input type="number" name="nota" class="form-control form-control-sm" id="nota">
              </div>
            </div>
            <div class="form-row mb-md-2">
              <div class="col">
                <label for="nome">WHERE nome =</label>
              </div>
              <label class="sr-only" for="sel">T</label>
              	<div class="col">
	                <select name="nome" class="form-control form-control-sm" id="nome">
	                  <option value="artur">artur</option>
	                  <option value="giuliano">giuliano</option>
	                  <option value="renan">renan</option>
	                </select>
                </div>
            </div>
            <div class="form-row mb-md-2">
              <div class="col">

              </div>
              <div class="col">
            	<input type="hidden" name="nome_formulario" value="novo_comando">
                <button type="submit" name="adicionar" class="btn btn-sm btn-secondary w-100">ADICIONAR</button>
              </div>
            </div>
          </form>
        </div>
        
        <div class="col-sm-4">
          <div class="w-100 h-100" style="background-color:#d9d9d9; overflow-y: scroll;">
            <code>
              <%! 
              	List<String> listaLogBuffer = new ArrayList<>();
              %>
              <%
              	listaLogBuffer = ReadWrite.readFile("log_buffer");
              %>
              
              
              <% if (listaLogBuffer != null && listaLogBuffer.size() > 0) { %>
              	<% for(String item : listaLogBuffer){ %>
              		<%= item %><br>
              	<% } %>
              <% } %>
              
            </code>
          </div>
        </div>
        <div class="col-sm-4">

          <div class="w-100 h-100" style="background-color:#d9d9d9; overflow-y: scroll;">
			<code>
              <%! 
              	List<String> listaDadosBuffer = new ArrayList<>();
              %>
              <%
              listaDadosBuffer = ReadWrite.readFile("dados_buffer");
              %>
              
              
              <% if (listaDadosBuffer != null && listaDadosBuffer.size() > 0) { %>
              	<% for(String item : listaDadosBuffer){ %>
              		<%= item %><br>
              	<% } %>
              <% } %>
              
            </code>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">

        </div>
        <div class="col">
          <h6>LOG (DISCO)</h6>
        </div>
        <div class="col">
          <h6>DADOS (DISCO)</h6>
        </div>
      </div>
      
      <!-- COMMITS -->
      <div class="row" style="height: 200px;">
        <div class="col">
			<h6>COMMITS</h6>
			<form action="Controle" method="POST">
            <div class="form-row mb-md-2">
              <div class="col">
                <label class="sr-only" for="sel">T</label>
                <select name="transacao_commit" class="form-control form-control-sm" id="sel">
                  <option value="T1">T1</option>
                  <option value="T2">T2</option>
                  <option value="T3">T3</option>
                  <option value="T4">T4</option>
                  <option value="T5">T5</option>
                </select>
              </div>
              <div class="col">
              	<input type="hidden" name="nome_formulario" value="commit">
                <button type="submit" class="btn btn-sm btn-secondary w-100">COMMIT</button>
              </div>
            </div>
          </form>
        </div>
        <div class="col">
          <div class="w-100 h-100 mb-md-2" style="background-color:#d9d9d9; overflow-y: scroll;">
			<code>
              <%! 
              	List<String> listaLogDisco = new ArrayList<>();
              %>
              <%
              	listaLogDisco = ReadWrite.readFile("log_disco");
              %>
              
              
              <% if (listaLogDisco != null && listaLogDisco.size() > 0) { %>
              	<% for(String item : listaLogDisco){ %>
              		<%= item %><br>
              	<% } %>
              <% } %>
              
            </code>
          </div>
        </div>
        <div class="col">
          <div class="w-100 h-100 mb-md-2" style="background-color:#d9d9d9; overflow-y: scroll;">
			<code>
              <%! 
              	List<String> listaDadosDisco = new ArrayList<>();
              %>
              <%
              listaDadosDisco = ReadWrite.readFile("dados_disco");
              %>
              
              
              <% if (listaDadosDisco != null && listaDadosDisco.size() > 0) { %>
              	<% for(String item : listaDadosDisco){ %>
              		<%= item %><br>
              	<% } %>
              <% } %>
              
            </code>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">

        </div>
        <div class="col-md-3 mt-md-2">
     	  <form action="Controle" method="POST">
          	<input type="hidden" name="nome_formulario" value="checkpoint">
          	<button type="submit" class="btn btn-success w-100">CHECKPOINT</button>
          </form>
        </div>
        <div class="col-md-5 mt-md-2">
	        <form action="Controle" method="POST">
	          	<input type="hidden" name="nome_formulario" value="erro">
          		<button type="submit" class="btn btn-danger w-100">ERRO</button>
          	</form>
        </div>
      </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>