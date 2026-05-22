# from odoo import http


# class VehiculosCliente(http.Controller):
#     @http.route('/vehiculos_cliente/vehiculos_cliente', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/vehiculos_cliente/vehiculos_cliente/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('vehiculos_cliente.listing', {
#             'root': '/vehiculos_cliente/vehiculos_cliente',
#             'objects': http.request.env['vehiculos_cliente.vehiculos_cliente'].search([]),
#         })

#     @http.route('/vehiculos_cliente/vehiculos_cliente/objects/<model("vehiculos_cliente.vehiculos_cliente"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('vehiculos_cliente.object', {
#             'object': obj
#         })

