# from odoo import http


# class Vehículos(http.Controller):
#     @http.route('/vehículos/vehículos', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/vehículos/vehículos/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('vehículos.listing', {
#             'root': '/vehículos/vehículos',
#             'objects': http.request.env['vehículos.vehículos'].search([]),
#         })

#     @http.route('/vehículos/vehículos/objects/<model("vehículos.vehículos"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('vehículos.object', {
#             'object': obj
#         })

