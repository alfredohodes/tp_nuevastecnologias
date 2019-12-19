<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Requerimiento Repuesto</title>
    </head>
    <body>
        <a href="#show-requerimientoRepuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="show" resource="${requerimientoRepuesto.ot}">Orden de Trabajo: ${requerimientoRepuesto.ot.id}</g:link></li>
            </ul>
        </div>
        <div id="show-requerimientoRepuesto" class="content scaffold-show" role="main">
            <h1>Requerimiento Repuesto</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <div class="row">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-bordered">
                        <tbody>
                            <tr>
                                <th scope="row">ID</th>
                                <td>${requerimientoRepuesto.id}</td>
                            </tr>
                            <tr>
                                <th scope="row">Tipo</th>
                                <td>
                                    <g:link class="show" action="show" resource="${requerimientoRepuesto.tipo}">
                                        ${requerimientoRepuesto.tipo.nombre}
                                    </g:link>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Cantidad</th>
                                <td>${requerimientoRepuesto.cantidad}</td>
                            </tr>
                            <tr>
                                <th scope="row">Orden de Trabajo</th>
                                <td>
                                    <g:link class="show" action="show" resource="${requerimientoRepuesto.ot}">
                                        ${requerimientoRepuesto.ot}
                                    </g:link>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Reservas repuestos</th>
                                <td>
                                    <ul class="list-inline">
                                        <g:each in="${requerimientoRepuesto.reservasRepuestos.sort { it.id  }}" var="reservaRepuesto">
                                            <li>
                                                <g:link class="show" action="show" resource="${reservaRepuesto}">
                                                     ${reservaRepuesto}
                                                </g:link>
                                            </li>
                                        </g:each>
                                    </ul>
                                </td>
                            </tr>
                        </tbody>
					</table>
				</div>
			</div>
            
            <g:form resource="${this.requerimientoRepuesto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.requerimientoRepuesto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
