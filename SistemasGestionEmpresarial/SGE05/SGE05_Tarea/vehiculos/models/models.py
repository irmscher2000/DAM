from odoo import models, fields, api


class Vehiculos(models.Model):
    _name = 'x_vehiculos.vehiculos'
    _description = 'Gestión de Vehículos'
    _rec_name = 'matricula' 


    # Campos requeridos
    marca = fields.Char(string='Marca', required=True)
    modelo = fields.Char(string='Modelo', required=True)
    anio_fabricacion = fields.Integer(string='Año de Fabricación', required=True)
    matricula = fields.Char(string='Matrícula', required=True, 
                           help='Formato: 1234-ABC')
    
    # Restricciones para validar los datos
    _sql_constraints = [
        ('matricula_unique', 'unique(matricula)', 'La matrícula debe ser única.')
    ]
