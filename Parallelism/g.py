import matplotlib.pyplot as plt

# -----------------------------
# Data (N = 100)
# -----------------------------
dataset = [1, 2, 3, 4, 5]  # ชุดข้อมูล (ครั้งที่ทดลอง)

sequential_time = [ 10.4757, 7.4986, 8.3114, 5.8942, 5.658]
parallel_time   = [13.3921, 1.5488, 1.2766, 1.5802, 1.0948]

# -----------------------------
# Plot
# -----------------------------
plt.figure(figsize=(8, 5))

plt.plot(dataset, sequential_time, marker='o', label='Sequential Task')
plt.plot(dataset, parallel_time, marker='o', label='Parallel Task')

plt.xlabel('Dataset')
plt.ylabel('Time (μs)')
plt.title('Sequential vs Parallel Sort Time (N = 10_000_000)')
plt.xticks(dataset)
plt.legend()
plt.grid(True)

plt.tight_layout()
plt.show()