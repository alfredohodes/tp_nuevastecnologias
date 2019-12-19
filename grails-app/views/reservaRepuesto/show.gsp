<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Reserva Repuesto</title>
    </head>
    <body>
        <a href="#show-reservaRepuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="show" resource="${reservaRepuesto.requerimiento}">Requerimiento repuesto: ${reservaRepuesto.requerimiento.id}</g:link></li>
            </ul>
        </div>
        <div id="show-reservaRepuesto" class="content scaffold-show" role="main">
            <h1>Reserva Repuesto</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <div class="row">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-bordered">
                        <tbody>
                            <tr>
                                <th scope="row">ID</th>
                                <td>${reservaRepuesto.id}</td>
                            </tr>
                            <tr>
                                <th scope="row">Cantidad</th>
                                <td>${reservaRepuesto.cantidad}</td>
                            </tr>
                            <tr>
                                <th scope="row">Disponibilidad</th>
                                <td>
                                    <g:link class="show" action="show" resource="${reservaRepuesto.disponibilidadRepuesto}">
                                        ${reservaRepuesto.disponibilidadRepuesto}
                                    </g:link>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Requerimiento</th>
                                <td>
                                    <g:link class="show" action="show" resource="${reservaRepuesto.requerimiento}">
                                        ${reservaRepuesto.requerimiento}
                                    </g:link>
                                </td>
                            </tr>
                        </tbody>
					</table>
				</div>
			</div>

            <g:form resource="${this.reservaRepuesto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.reservaRepuesto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
