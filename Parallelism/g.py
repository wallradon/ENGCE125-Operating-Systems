import matplotlib.pyplot as plt

# -----------------------------
# Data (N = 100)
# -----------------------------
dataset = [1, 2, 3, 4, 5]  # ชุดข้อมูล (ครั้งที่ทดลอง)

sequential_time = [579.8217, 590.6132, 634.3969, 605.997, 645.5426]
parallel_time   = [281.2398, 205.2877, 198.839, 199.1995, 221.161]

# -----------------------------
# Plot
# -----------------------------
plt.figure(figsize=(8, 5))

plt.plot(dataset, sequential_time, marker='o', label='Sequential Sort')
plt.plot(dataset, parallel_time, marker='o', label='Parallel Sort')

plt.xlabel('Dataset')
plt.ylabel('Time (μs)')
plt.title('Sequential vs Parallel Sort Time (N = 10_000_000)')
plt.xticks(dataset)
plt.legend()
plt.grid(True)

plt.tight_layout()
plt.show()