<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inicio</title>    
</head>

<body>
  <div class="container">
  
      <h1>USER STORIES</h1>
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
                <li> <g:link action="prepararUS1_1">Preparar US 1.1</g:link></li>
              </ul>
            </div>
          </div>
        </div>
      </div>

  </div>
</body>
</html>
