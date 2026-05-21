extends Area2D

# Preparacion para las colisiones
signal hit

@export var speed = 400
var screen_size
var bullet = preload("res://bullet.tscn")

# Called when the node enters the scene tree for the first time.
func _ready():
	screen_size = get_viewport_rect().size
	# Ocultar el jugador al iniciar el juego
	hide()

# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	# Hacer que el jugador mire al ratón
	look_at(get_global_mouse_position())
	
	# Movimiento
	var velocity = 0
	if Input.is_action_pressed("move_right"):
		velocity += 1
	if Input.is_action_pressed("move_left"):
		velocity -= 1

	if velocity != 0:
		$AnimatedSprite2D.play()
	else:
		$AnimatedSprite2D.stop()
		
	# Movimiento 
	position.x += velocity * speed * delta
	
	# Limitar pantalla
	position.x = clamp(position.x, 0, screen_size.x)
	
	# Disparo
	if Input.is_action_just_pressed("ui_accept"):
		shoot()
	

func _on_body_entered(body: Node2D):
	hide() 
	hit.emit()
	$CollisionShape2D.set_deferred("disabled", true)
	
func start():
	var screen_size = get_viewport_rect().size
	position = Vector2(screen_size.x / 2, screen_size.y -50)
	show()
	$CollisionShape2D.disabled = false

func shoot():
	var newBullet = bullet.instantiate()
	newBullet.direction = rotation
	newBullet.global_position = $SpawnPoint.global_position
	get_parent().add_child(newBullet)
	$Disparo.play()
	
	
