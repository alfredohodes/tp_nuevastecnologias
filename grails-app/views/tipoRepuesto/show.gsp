<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Tipo Repuesto</title>
    </head>
    <body>
        <a href="#show-tipoRepuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><a href="#" onclick="javascript:window.history.back();">Volver</a></li>
            </ul>
        </div>
        <div id="show-tipoRepuesto" class="content scaffold-show" role="main">
            <h1>Tipo Repuesto</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="row">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-bordered">
                        <tbody>
                            <tr>
                                <th scope="row">ID</th>
                                <td>${tipoRepuesto.id}</td>
                            </tr>
                            <tr>
                                <th scope="row">Nombre</th>
                                <td>${tipoRepuesto.nombre}</td>
                            </tr>
                            <tr>
                                <th scope="row">Código</th>
                                <td>${tipoRepuesto.codigo}</td>
                            </tr>
                            <tr>
                                <th scope="row">Cantidad alerta stock mínimo</th>
                                <td>${tipoRepuesto.cantidadAlertaStockMinimo}</td>
                            </tr>
                        </tbody>
					</table>
				</div>
			</div>

            <g:form resource="${this.tipoRepuesto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.tipoRepuesto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
