<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inicio</title>    
</head>

<body>
  <div class="container">
  
      <h1 class="text-center">Historias de usuario</h1>
      <g:if test="${flash.message}">
        <div class="row">
          <div class="col-md-12">
            <div class="message" role="status">${flash.message}</div>
          </div>
        </div>
      </g:if>

      <div class="row">
        <div class="col-md-12">
          <div class="navbar navbar">
            <div class="container-fluid">
              <ul class="nav navbar">
                <li class="nav-tabs"><h4><g:link action="prepararUS1_1">Preparar US 1.1</g:link></h4></li>
                <li class="nav-tabs"><h4><g:link action="prepararUS1_2">Preparar US 1.2</g:link></h4></li>
                <li class="nav-tabs"><h4><g:link action="prepararUS2">Preparar US 2</g:link></h4></li>
                <li class="nav-tabs"><h4><g:link action="prepararUS3_1">Preparar US 3.1</g:link></h4></li>
                <li class="nav-tabs"><h4><g:link action="prepararUS3_2">Preparar US 3.2</g:link></h4></li>
              </ul>
            </div>
          </div>
        </div>
      </div>

  </div>
</body>
</html>
