<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inicio</title>    
</head>

<body>
  <div class="container">

      <g:if test="${flash.message}">
        <div class="row">
          <div class="col-md-12">
            <div class="message" role="status">${flash.message}</div>
          </div>
        </div>
      </g:if>
      
      <div class="row">
        <div class="col-md-12">
          <div class="navbar navbar-horizontal">
            <div class="container-fluid">
              <ul class="nav navbar-horizontal">
                <li class="nav-tabs"><h1><g:link controller="ordenDeTrabajo" action="index">Órdenes de trabajo</g:link></h1></li>
                <li class="nav-tabs"><h1><g:link controller="compraRepuesto" action="index">Compras</g:link></h1></li>
                <li class="nav-tabs"><h1><g:link controller="userStories" action="index">Historias de usuario</g:link></h1></li>
              </ul>
            </div>
          </div>
        </div>
      </div>

    </br>

  </div>
</body>
</html>
