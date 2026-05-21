extends Node

@export var mob_scene: PackedScene
var score

# Called when the node enters the scene tree for the first time.
func _ready():
	pass


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float) -> void:
	pass


func game_over():
	$ScoreTimer.stop()
	$MobTimer.stop()
	$HUD.show_game_over()
	$Music.stop()
	$DeathSound.play()
	
func new_game():
	score = 0
	$Jugador.start()
	$StartTimer.start()
	$HUD.update_score(score)
	$HUD.show_message("Preparado!")
	get_tree().call_group("mobs", "queue_free")
	$Music.play()


func _on_start_timer_timeout():
	$MobTimer.start()
	$ScoreTimer.start()


func _on_score_timer_timeout():
	score += 1
	$HUD.update_score(score)


func _on_mob_timer_timeout():
	# Screa una nueva instancia de la escena mob
	var mob = mob_scene.instantiate()

	# Selecciona una posicion aleatoria evitando la parte inferior
	var mob_spawn_location = $MobPath/MobSpawnLocation
	mob_spawn_location.progress_ratio = randf_range(0.0, 0.50)

	# Configura la posicion del mob en la posicion aleatoria
	mob.position = mob_spawn_location.position

	# Establece la direccion del mob, siempre hacia abajo
	var direction = PI / 2
	direction += randf_range(-PI /6, PI /6)

	# Añade algo de aleatoriedad a la dirección.
	direction += randf_range(-PI / 4, PI / 4)
	mob.rotation = direction

	# Elige la velocidad para el el mob
	var velocity = Vector2(randf_range(150.0, 250.0), 0.0)
	mob.linear_velocity = velocity.rotated(direction)

	# Genera el mob añadiéndolo a la escena principal.
	add_child(mob)
	
func  add_score(points):
	score += points
	$HUD.update_score(score)
