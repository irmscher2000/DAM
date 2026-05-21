extends Area2D

var direction: float = 0.0
var speed: float = 1000.0

func _ready():
	rotation = direction

func _process(delta):
	# Movimiento manual
	position += Vector2(speed, 0).rotated(direction) * delta
	
	# Eliminar la bala si sale de la pantalla
	if position.x < 0 or position.x > get_viewport_rect().size.x or position.y < 0 or position.y > get_viewport_rect().size.y:
		queue_free()

func _on_body_entered(body: Node2D):
	# Verificar si el cuerpo que tocamos es un mob (enemigo)
	if body.is_in_group("mobs"):
		
		# Sonido del impacto de la bala con el mob
		get_tree().current_scene.get_node("Impacto").play()
		# Eliminar el mob
		body.queue_free()
		
		# Eliminar la bala
		queue_free()
		
		# Añadir puntos (llamamos a la función en la escena principal)
		get_tree().current_scene.add_score(10)
		
