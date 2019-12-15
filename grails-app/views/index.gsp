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
                <li> <g:link controller="ordenDeTrabajo" action="index">Ordenes de trabajo</g:link></li>
                <li> <g:link controller="compraRepuesto" action="index">Compras</g:link></li>
                <li> <g:link controller="userStories" action="index">User Stories</g:link></li>
              </ul>
            </div>
          </div>
        </div>
      </div>

    </br>

  </div>
</body>
</html>
