import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// Place your Spring DSL code here
beans = {
    localeResolver(SessionLocaleResolver) { 
        defaultLocale= new java.util.Locale('es'); 
    }

    ordenDeTrabajoRepository(aerorep.OrdenDeTrabajoRepository)
    disponibilidadRepuestoRepository(aerorep.DisponibilidadRepuestoRepository)
    compraRepuestoRepository(aerorep.CompraRepuestoRepository)
    detalleCompraRepuestoRepository(aerorep.DetalleCompraRepuestoRepository)
}
