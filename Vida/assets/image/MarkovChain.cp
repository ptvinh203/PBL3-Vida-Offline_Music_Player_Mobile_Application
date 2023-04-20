#include <iostream>
#include <math.h>
#include <iomanip>
#include <Eigen/Dense>

using namespace std;
using namespace Eigen;

MatrixXd inputMatrix(int row, int col)
{
    MatrixXd A(row, col);
    for (int i = 0; i < A.rows(); i++)
      for (int j = 0; j < A.cols(); j++)
        cin >> A(i, j);
    return A;
}
void printMatrix(MatrixXd matrix)
{
    for (int i = 0; i < matrix.rows(); i++)
    {
        for (int j = 0; j < matrix.cols(); j++)
        {
            cout << setprecision(5) << fixed << matrix(i, j) << "   ";
        }
        cout << endl;
    }
}
MatrixXd mutiplyMatrix(MatrixXd a, MatrixXd b)
{
    if (a.cols() != b.rows())
      return MatrixXd();
    else
    {
      MatrixXd temp(a.rows(), b.cols());
      for (int i = 0; i < a.rows(); i++)
      {
        for (int j = 0; j < b.cols(); j++)
        {
          double sum = 0;
          for (int k = 0; k < a.cols(); k++)
            sum += a(i, k) * b(k, j);
          temp(i, j) = sum;
        }
      }
      return temp;
    }
}
// MatrixXd guest(int day, MatrixXd P, MatrixXd D, MatrixXd P_inverse) 
// {
//     for (int i = 1; i <= day - 1; i++) {
//         D = mutiplyMatrix(D, D);
//     }
//     return mutiplyMatrix(mutiplyMatrix(P, P_inverse), D);
// }
int main()
{
    int size;
    cout << "Size: "; cin >> size;

    MatrixXd A = inputMatrix(size, size);
    
    int day;
    cout << "Enter the day after: "; cin >> day;
    cout << "Probability after " << day << ": "<< endl;
    for (int i = 1; i <= day - 1; i++) {
        A = mutiplyMatrix(A, A);
    }
    cout << A << endl;
    return 0;
}