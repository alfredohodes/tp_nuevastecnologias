<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Orden de Trabajo: ${ordenDeTrabajo.id}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-ordenDeTrabajo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-ordenDeTrabajo" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="row">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-bordered">
                        <tbody>
                            <tr>
                                <th scope="row">ID</th>
                                <td>${ordenDeTrabajo.id}</td>
                            </tr>
                            <tr>
                                <th scope="row">Estado</th>
                                <td>${ordenDeTrabajo.estado}</td>
                            </tr>
                            <tr>
                                <th scope="row">Requerimientos repuestos</th>
                                <td>
                                    <ul class="list-inline">
                                        <g:each in="${ordenDeTrabajo.requerimientoRepuestos.sort { it.id  }}" var="requerimientoRepuestoInstance">
                                            <li>
                                                <g:link class="show" action="show" resource="${requerimientoRepuestoInstance}">
                                                     ${requerimientoRepuestoInstance}
                                                </g:link>
                                            </li>
                                        </g:each>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Reservas repuestos</th>
                                <td>
                                    <ul class="list-inline">
                                        <g:each in="${reservasRepuestos.sort { it.id  }}" var="reservaRepuestoInstance">
                                            <li>
                                                <g:link class="show" action="show" resource="${reservaRepuestoInstance}">
                                                     ${reservaRepuestoInstance}
                                                </g:link>
                                            </li>
                                        </g:each>
                                    </ul>
                                </td>
                            </tr>
                            <g:if test="${puedeSerEjecutada}">
                                <tr>
                                    <th scope="row">Valor OT (30% ganancia)</th>
                                    <td><g:formatNumber number="${valorOT.monto}" type="currency" currencyCode="ARS"/></td>
                                </tr>
                            </g:if>
                        </tbody>
					</table>
                    
                    <g:if test="${puedeSerEjecutada}">
                        <g:form action="ejecutarOT" params="[otId:"${ordenDeTrabajo.id}"]">
                            <g:actionSubmit action="ejecutarOT" value="Ejecutar" class="btn btn-success pull-right" style="margin-top:10px;" />
                        </g:form>
                    </g:if>
                    <g:elseif test="${estaEjecutada}">
                        <h3>LISTADO DE REPUESTOS A USAR</h3>
                        <g:each in="${reservasRepuestos.sort { it.id  }}" var="reservaRepuestoInstance">
                            <li>
                                ${reservaRepuestoInstance.disponibilidadRepuesto.tipo.nombre} x ${reservaRepuestoInstance.cantidad} - Ubicación: 
                                ${reservaRepuestoInstance.disponibilidadRepuesto.ubicacion}
                            </li>
                        </g:each>


                    </g:elseif>
                    <g:else>
                        <g:form action="prepararOT" params="[otId:"${ordenDeTrabajo.id}"]">
                            <g:actionSubmit action="prepararOT" value="Preparar" class="btn btn-warning pull-right" style="margin-top:10px;" />
                        </g:form>
                    </g:else>



				</div>
			</div>
        </div>
        <br/>
        <g:form resource="${this.ordenDeTrabajo}" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${this.ordenDeTrabajo}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
            </fieldset>
        </g:form>
    </body>
</html>
