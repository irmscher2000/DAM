from odoo import models, fields, api


class Cliente(models.Model):
    _name = 'x_vehiculos.cliente'
    _description = 'Clientes'
    _rec_name = 'nombre'

    dni = fields.Char(string="DNI", required=True)
    nombre = fields.Char(string="Nombre", required=True)
    telefono = fields.Char(string="Teléfono")

    # Restricciones para validar los datos
    _sql_constraints = [
        ('dni_unique', 'unique(dni)', 'El DNI debe ser único.')
    ]

class Vehiculos(models.Model):
    _inherit = 'x_vehiculos.vehiculos'

    cliente_id = fields.Many2one('x_vehiculos.cliente', string="Cliente")