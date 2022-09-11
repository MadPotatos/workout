package com.example.workout

object Constants {
    fun defaultExerciseList() : ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val warmUp = ExerciseModel(1,"Warm Up",R.drawable.warm_up,false,false)
        exerciseList.add(warmUp)
        val wallPushUp = ExerciseModel(2,"Wall Push Up",R.drawable.wall_push_up,false,false)
        exerciseList.add(wallPushUp)
        val plank = ExerciseModel(3,"Plank",R.drawable.plank,false,false)
        exerciseList.add(plank)
        val pushUp = ExerciseModel(4,"Push Up",R.drawable.push_up,false,false)
        exerciseList.add(pushUp)
        val run = ExerciseModel(5,"Run",R.drawable.run,false,false)
        exerciseList.add(run)
        val squat = ExerciseModel(6,"Squat",R.drawable.squat,false,false)
        exerciseList.add(squat)
        val dumbbellLift = ExerciseModel(7,"Lift the Dumbbbells",R.drawable.dumbbell_push,false,false)
        exerciseList.add(dumbbellLift)
        val absCrunch = ExerciseModel(8,"Abdominal Crunch",R.drawable.abs_crunch,false,false)
        exerciseList.add(absCrunch)
        return exerciseList

    }
}