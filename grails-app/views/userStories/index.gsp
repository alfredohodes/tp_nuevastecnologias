<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inicio</title>    
</head>

<body>

  <div class="nav" role="navigation">
      <ul>
          <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      </ul>
  </div>

  <h1 class="text-center">Historias de usuario</h1>

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
          <div class="navbar navbar">
            <div class="container-fluid">
              <ul class="nav navbar">
                <li class="nav-tabs"><h6><g:link action="prepararUS1_1">Preparar US 1.1</g:link></h6></li>
                <li class="nav-tabs"><h6><g:link action="prepararUS1_2">Preparar US 1.2</g:link></h6></li>
                <li class="nav-tabs"><h6><g:link action="prepararUS2">Preparar US 2</g:link></h6></li>
                <li class="nav-tabs"><h6><g:link action="prepararUS3_1">Preparar US 3.1</g:link></h6></li>
                <li class="nav-tabs"><h6><g:link action="prepararUS3_2">Preparar US 3.2</g:link></h6></li>
              </ul>
            </div>
          </div>
        </div>
      </div>

  </div>
</body>
</html>
