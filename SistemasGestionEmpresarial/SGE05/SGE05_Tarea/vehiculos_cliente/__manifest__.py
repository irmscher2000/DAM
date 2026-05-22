{
    'name': "Vehiculos Cliente",

    'summary': "Extension de vehiculos con clientes",

    'description': """ Para añadir clientes a los vehiculos """,

    'author': "Eugen Moga",
    'website': "https://www.yourcompany.com",

    'category': 'Extra',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base', 'vehiculos'],

    # always loaded
    'data': [
        'security/ir.model.access.csv',
        'views/views.xml',
    ],
    'installable': True,
    'application': True,
}

